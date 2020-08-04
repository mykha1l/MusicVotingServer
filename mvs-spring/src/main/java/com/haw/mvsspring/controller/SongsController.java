package com.haw.mvsspring.controller;

import java.io.IOException;
import java.util.List;

import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.model.SongList;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class SongsController {

    @GetMapping("/api/v1/songs")
    public List<Song> getSongList() throws UnsupportedTagException, InvalidDataException, IOException {
        return new SongList("../MVS-WebApp/songs").getSongList();
    }

    @GetMapping("/api/v1/pairs")
    public List<Song[]> getSongPairs() throws UnsupportedTagException, InvalidDataException, IOException {
        return new SongList("../MVS-WebApp/songs").getSongPairs();
    }

}
