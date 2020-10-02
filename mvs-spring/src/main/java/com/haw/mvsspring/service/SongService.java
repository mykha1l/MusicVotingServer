package com.haw.mvsspring.service;

import java.io.IOException;
import java.util.List;

import com.haw.mvsspring.dao.SongDao;
import com.haw.mvsspring.model.Song;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private final SongDao songDao;

    @Autowired
    public SongService(@Qualifier("sqlite") SongDao songDao)
            throws UnsupportedTagException, InvalidDataException, IOException {
        songDao.init();
        this.songDao = songDao;
    }

    public int addSong(Song song) {
        return songDao.insertSong(song);
    }

    public List<Song> getAllSongs() {
        return songDao.getAllSongs();
    }

}
