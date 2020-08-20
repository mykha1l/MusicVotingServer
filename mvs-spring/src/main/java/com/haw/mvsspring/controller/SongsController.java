package com.haw.mvsspring.controller;

import java.io.IOException;
import java.util.List;

import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.model.SongList;
import com.haw.mvsspring.model.VotesHandler;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class SongsController {

    final int MAX_VOTERS = 2; // for testing and developing. TODO: remove this line
    final int MAX_SONGS_SELECTED = 3; // for testing and developing. TODO: remove this line

    @GetMapping("/api/v1/songs")
    public List<Song> getSongList() throws UnsupportedTagException, InvalidDataException, IOException {
        return new SongList("../MVS-WebApp/songs").getSongList();
    }

    @GetMapping("/api/v1/pairs")
    public List<Song[]> getSongPairs() throws UnsupportedTagException, InvalidDataException, IOException {
        return new SongList("../MVS-WebApp/songs").getSongPairs();
    }

    @PostMapping("/api/v1/vote")
    void newEmployee(@RequestBody List<String> songs) {
        VotesHandler.votes.add(songs);
        if (VotesHandler.votes.size() == MAX_VOTERS) {
            System.out.println("Mostly voted songs: " + VotesHandler.getMostlyVotedSongs(MAX_SONGS_SELECTED));
        }
        System.out.println("received votes: " + songs.toString());
    }

}
