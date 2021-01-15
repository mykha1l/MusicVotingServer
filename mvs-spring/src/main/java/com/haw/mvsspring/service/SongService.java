package com.haw.mvsspring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Song[]> getSongPairs() {
        
        final var evenSongList = getEvenSongList();
        
        return getPairsList(evenSongList);
    }

    private List<Song> getEvenSongList() {
        final var songList = getAllSongs();
        if ((songList.size() % 2) != 0) {
            songList.remove(songList.size() - 1);
        }

        return songList;
    }

    private List<Song[]> getPairsList(List<Song> songList) {

        final List<Song[]> votingList = new ArrayList<>();
        for (int i = 0; i < songList.size(); i = i + 2) {
            final Song[] pair = new Song[2];
            pair[0] = songList.get(i);
            pair[1] = songList.get(i + 1);
            votingList.add(pair);
        }

        return votingList;
    }

    public int deleteSong(Integer id) {
        songDao.deleteSong(id);
        return 0;
    }
    
    public List<Song> searchInDB(final Map<String, String> searchItems){
        return songDao.searchInDB(searchItems);
    }
}
