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
import com.haw.mvsspring.Exceptions.MyDatabaseException;
import com.haw.mvsspring.Exceptions.NoContentException;
import com.haw.mvsspring.Exceptions.WrongDataException;
import com.haw.mvsspring.authentication.SecurityConfig;
import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.service.VotesHandler;
import com.haw.mvsspring.service.SongService;
import com.haw.mvsspring.service.UserService;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
            throw new WrongDataException("Too many search parameters");
        }

        if (!Song.searchableFields.contains((String) keySet.toArray()[0])) {
            throw new WrongDataException("This search Key  does not exist: " + (String) keySet.toArray()[0]);
        }

        try {
            final List<Song> songList = songService.searchInDB(params);
            if (songList.isEmpty()) {
                throw new NoContentException();
            } else {
                return songList;
            }

        } catch (DataAccessException e) {
            throw new MyDatabaseException(e);
        }

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

    @PutMapping(value = "/api/v1/songs/{id}")
    public void changeSongInfo(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        songService.changeSongInfo(id, body);
    }

    @GetMapping("/api/v1/get-voters-number")
    public int getVotersNumber() {
        return votesHandler.votersNumber;
    }

    @PostMapping(value = "/api/v1/set-voters-number")
    public void setVotersNumber(@RequestBody Map<String, Integer> body) {
        final var keySet = body.keySet();
        final var value = body.get((String)keySet.toArray()[0]);
        votesHandler.votersNumber= value;
    }

    @GetMapping("/api/v1/get-playlist-size")
    public int getPlayListSize() {
        return votesHandler.playlistSize;
    }

    @PostMapping(value = "/api/v1/set-playlist-size")
    public void setPlayListSize(@RequestBody Map<String, Integer> body) {
        final var keySet = body.keySet();
        final var value = body.get((String)keySet.toArray()[0]);
        votesHandler.playlistSize= value;
    }
}
