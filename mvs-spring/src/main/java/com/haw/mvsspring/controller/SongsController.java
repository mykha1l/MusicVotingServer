package com.haw.mvsspring.controller;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.haw.mvsspring.service.MyPlayer;
import com.haw.mvsspring.Exceptions.FileUploadException;
import com.haw.mvsspring.authentication.SecurityConfig;
import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.model.User;
import com.haw.mvsspring.model.UserDTO;
import com.haw.mvsspring.service.VotesHandler;
import com.haw.mvsspring.service.SongService;
import com.haw.mvsspring.service.UserService;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javazoom.jl.decoder.JavaLayerException;

@RequestMapping
@RestController
public class SongsController {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private VotesHandler votesHandler;

    @Autowired
    private MyPlayer myPlayer;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping("/api/v1/songs")
    public List<Song> getSongList() throws UnsupportedTagException, InvalidDataException, IOException {
        return songService.getAllSongs();
    }

    @GetMapping("/api/v1/pairs")
    public List<Song[]> getSongPairs() throws UnsupportedTagException, InvalidDataException, IOException {
        return songService.getSongPairs();
    }

    @PostMapping("/api/v1/vote")
    void submitVotes(@RequestBody final List<String> songs)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
        votesHandler.votes.add(songs);
        if (votesHandler.votes.size() == votesHandler.votersNumber) {
            System.out.println("Mostly voted songs: " + votesHandler.calculateMostlyVotedSongs());
            myPlayer.play(votesHandler.calculateMostlyVotedSongs());
            votesHandler.votes.clear();
        }
        System.out.println("received votes: " + songs.toString());
    }

    @PostMapping("/api/v1/songs/upload")
    public ResponseEntity upload(@RequestBody MultipartFile file) {
        System.out.println("received upload: " + file.getOriginalFilename());
        final String filePath = "../MVS-WebApp/songs/" + file.getOriginalFilename();
        final File localFile = new File(filePath);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            final Song song = new Song(localFile);
            songService.addSong(song);
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
            localFile.delete();
            throw new FileUploadException(e);
        } catch (InvalidDataException e) {
            e.printStackTrace();
            localFile.delete();
            throw new FileUploadException(e);
        } catch (IOException e) {
            e.printStackTrace();
            localFile.delete();
            throw new FileUploadException(e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/v1/songs/current")
    public String getCurrentSong() {
        return myPlayer.currentSong;
    }

    @GetMapping("/api/v1/songs/mostlyVoted")
    public ArrayList<String> mostlyVotedSongs() {
       return votesHandler.mostlyVoted; 
    }

    @GetMapping("/api/v1/songs/current/stop")
    public void stopPlaying() {
        myPlayer.stop();
    }

    @GetMapping("/api/v1/user")
    public String getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/api/v1/register")
    RedirectView registerUser(@ModelAttribute final UserDTO userDTO, HttpServletRequest req) {
        final User user = new User(userDTO.getUsername(),
                securityConfig.passwordEncoder().encode(userDTO.getPassword()), "ROLE_USER", true);
        userService.addUser(user);
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userDTO.getUsername(),
                userDTO.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
        return new RedirectView("/index.html", false);
    }

    @PostMapping("/login")
    RedirectView login(@ModelAttribute final UserDTO userDTO, HttpServletRequest req) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userDTO.getUsername(),
                userDTO.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
        return new RedirectView("/index.html", false);
    }

}
