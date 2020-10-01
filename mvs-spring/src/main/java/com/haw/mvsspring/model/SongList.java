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
        
        makeSongListEven();
        
        return makePairsList();
    }

    private void makeSongListEven() {
        var songList = songService.getAllSongs();
        if ((songList.size() % 2) != 0) {
            songList.remove(songList.size() - 1);
        }
    }

    private List<Song[]> makePairsList() {

        final List<Song[]> votingList = new ArrayList<>();
        var songList = songService.getAllSongs();
        for (int i = 0; i < songList.size(); i = i + 2) {
            final Song[] pair = new Song[2];
            pair[0] = songList.get(i);
            pair[1] = songList.get(i + 1);
            votingList.add(pair);
        }

        return votingList;
    }

    

}
