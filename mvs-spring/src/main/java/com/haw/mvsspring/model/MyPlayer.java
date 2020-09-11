package com.haw.mvsspring.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class MyPlayer {

    public static String currentSong;

    public static void play(List<String> filenames)
            throws FileNotFoundException, JavaLayerException, UnsupportedEncodingException {

        for (final String filename : filenames) {
            final File file = new File("../MVS-WebApp/songs/" + filename);
            final FileInputStream fis = new FileInputStream(file);
            final Player playMP3 = new Player(fis);
            currentSong = filename;
            playMP3.play(300);
            playMP3.close();
        }
    }

}
