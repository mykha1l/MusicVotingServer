package com.haw.mvsspring.model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import org.apache.commons.io.filefilter.WildcardFileFilter;

public class SongList {

    public static ArrayList<Song> init(final String path) {

        ArrayList<Song> songList = new ArrayList<>();
        final File file = new File(path);
        final FileFilter fileFilter = new WildcardFileFilter("*.mp3");
        final File[] fileList = file.listFiles(fileFilter);
        for (final File song : fileList) {
            songList.add(new Song(song.getName()));
        }

        return songList;
    }

}
