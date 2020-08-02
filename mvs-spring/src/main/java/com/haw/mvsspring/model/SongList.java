package com.haw.mvsspring.model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;

public class SongList {

    private ArrayList<Song> songList;

    public SongList(final String path) {
        songList = new ArrayList<>();
        final File file = new File(path);
        final FileFilter fileFilter = new WildcardFileFilter("*.mp3");
        final File[] fileList = file.listFiles(fileFilter);
        for (final File song : fileList) {
            songList.add(new Song(song.getName()));
        }
    }

    public List<Song> getSongList() {
        return songList;
    }

    public List<List<Song>> getSongPairs() {
        List<List<Song>> votingList = new ArrayList<>();
        int i = 0;
        while (i < songList.size()) {
            List<Song> pair = new ArrayList<>();
            pair.add(songList.get(i));
            if (++i < songList.size()) {
                pair.add(songList.get(i));
            }
            votingList.add(pair);
        }

        return votingList;
    }

}
