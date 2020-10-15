package com.haw.mvsspring.controller;

import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.haw.mvsspring.service.MyPlayer;
import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.service.VotesHandler;
import com.haw.mvsspring.service.SongService;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javazoom.jl.decoder.JavaLayerException;

@RequestMapping
@RestController
public class SongsController {

    @Autowired
    private SongService songService;

    @Autowired
    private VotesHandler votesHandler;

    @Autowired
    private MyPlayer myPlayer;

    @GetMapping("/api/v1/songs")
    public List<Song> getSongList() throws UnsupportedTagException, InvalidDataException, IOException {
        return songService.getAllSongs();
    }

    @GetMapping("/api/v1/pairs")
    public List<Song[]> getSongPairs() throws UnsupportedTagException, InvalidDataException, IOException {
        return songService.getSongPairs();
    }

    @PostMapping("/api/v1/vote")
    void submitVotes(@RequestBody List<String> songs)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException {
        votesHandler.votes.add(songs);
        if (votesHandler.votes.size() == votesHandler.votersNumber) {
            System.out.println("Mostly voted songs: " + votesHandler.calculateMostlyVotedSongs());
            myPlayer.play(votesHandler.calculateMostlyVotedSongs());
            votesHandler.votes.clear();
        }
        System.out.println("received votes: " + songs.toString());
    }

    @GetMapping("/api/v1/currentSong")
    public String getCurrentSong() {
        return myPlayer.currentSong;
    }

    @GetMapping("/api/v1/mostlyVoted")
    public ArrayList<String> mostlyVotedSongs() {
       return votesHandler.mostlyVoted; 
    }

    @GetMapping("/api/v1/stop")
    public void stopPlaying() {
        myPlayer.stop();
    }

}
