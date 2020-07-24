package com.haw.mvsspring.controller;

import java.util.List;

import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.model.SongList;

import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class SongsController {

    @GetMapping("/api/v1/songs")
    public List<Song> getSongList() {
        return SongList.init("../MVS-WebApp/songs");
    }

}
