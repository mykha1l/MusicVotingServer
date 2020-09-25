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
    public static Player player;

    public static void play(List<String> filenames)
            throws FileNotFoundException, JavaLayerException, UnsupportedEncodingException {

        for (final String filename : filenames) {
            final File file = new File("../MVS-WebApp/songs/" + filename);
            final FileInputStream fis = new FileInputStream(file);
            player = new Player(fis);
            currentSong = filename;
            player.play(300);
            player.close();
        }
    }

    public static void stop() {
        if (player != null) {
            player.close();
        }
    }

}
