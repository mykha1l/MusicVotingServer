package com.haw.mvsspring.model;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.apache.commons.io.filefilter.WildcardFileFilter;

public class SongList {

    private ArrayList<Song> songList;

    public SongList(final String path) throws UnsupportedTagException, InvalidDataException, IOException {
        songList = new ArrayList<>();
        final File file = new File(path);
        final FileFilter fileFilter = new WildcardFileFilter("*.mp3");
        final File[] fileList = file.listFiles(fileFilter);
        for (final File song : fileList) {
            songList.add(new Song(song));
        }
    }

    public List<Song> getSongList() {
        return songList;
    }

    public List<Song[]> getSongPairs() {
        Collections.shuffle(songList);
        final List<Song[]> votingList = new ArrayList<>();
        int i = 0;
        while (i < songList.size()) {
            final Song[] pair = new Song[2];
            pair[0] = songList.get(i);
            if (++i < songList.size()) {
                pair[1] = songList.get(i);
            }
            votingList.add(pair);
            ++i;
        }

        return votingList;
    }

}
