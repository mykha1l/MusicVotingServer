package com.haw.mvsspring.controller;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.haw.mvsspring.service.MyPlayer;
import com.haw.mvsspring.Exceptions.FileUploadException;
import com.haw.mvsspring.authentication.SecurityConfig;
import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.service.VotesHandler;
import com.haw.mvsspring.service.SongService;
import com.haw.mvsspring.service.UserService;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/api/v1/songs")
    public List<Song> getSongList(@RequestParam final Map<String, String> params)
            throws UnsupportedTagException, InvalidDataException, IOException {

        if (params.isEmpty()) {
            return songService.getAllSongs();
        }

        final var keySet = params.keySet();
        if (keySet.size() > 1) {
            return new ArrayList<Song>();
        }

        if (!Song.searchableFields.contains((String)keySet.toArray()[0])){
            return new ArrayList<Song>();
        }

        return songService.searchInDB(params);
    }

    @GetMapping("/api/v1/pairs")
    public List<Song[]> getSongPairs() throws UnsupportedTagException, InvalidDataException, IOException {
        return songService.getSongPairs();
    }

    @PostMapping("/api/v1/vote")
    void submitVotes(@RequestBody final List<String> songs)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        votesHandler.votes.add(songs);
        for (final String song : songs) {
            userService.storeUsersVote(username, song);
        }
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

    @DeleteMapping(value = "/api/v1/songs/{id}")
    public void deleteSong(@PathVariable Integer id) {
        songService.deleteSong(id);
    }

}
