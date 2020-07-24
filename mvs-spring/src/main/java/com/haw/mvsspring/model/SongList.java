package com.haw.mvsspring.model;

import java.io.File;
import java.util.ArrayList;

public class SongList {

    public static ArrayList<Song> init(final String path) {

        ArrayList<Song> songList = new ArrayList<>();
        final File file = new File(path);
        final String[] fileList = file.list();
        for (final String name : fileList) {
            songList.add(new Song(name));
        }

        return songList;
    }

}
