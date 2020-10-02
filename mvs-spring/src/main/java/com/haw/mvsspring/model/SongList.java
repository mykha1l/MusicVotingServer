package com.haw.mvsspring.model;

import java.util.ArrayList;
import java.util.List;

import com.haw.mvsspring.service.SongService;

public class SongList {

    private SongService songService;

    public SongList(SongService songService) {
        this.songService = songService;
    }

    public List<Song> getSongList() {
        return songService.getAllSongs();
    }

    public List<Song[]> getSongPairs() {
        
        final var evenSongList = getEvenSongList();
        
        return getPairsList(evenSongList);
    }

    private List<Song> getEvenSongList() {
        final var songList = getSongList();
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

    

}
