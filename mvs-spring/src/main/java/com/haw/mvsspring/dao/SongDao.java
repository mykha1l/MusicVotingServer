package com.haw.mvsspring.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.haw.mvsspring.model.Song;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

public interface SongDao {

    int insertSong(Song song);

    List<Song> getAllSongs();

    int init() throws UnsupportedTagException, InvalidDataException, IOException;
    
    int deleteSong(Integer id);

    List<Song> searchInDB(final Map<String, String> searchItems);
    
}
