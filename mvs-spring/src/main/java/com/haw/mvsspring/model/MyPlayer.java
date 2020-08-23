package com.haw.mvsspring.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class MyPlayer {

    public static void play(List<String> filenames)
            throws FileNotFoundException, JavaLayerException, UnsupportedEncodingException {
        final File file = new File("../MVS-WebApp/songs/" + URLEncoder.encode(filenames.get(0), "UTF-8" ));
        final FileInputStream fis = new FileInputStream(file);
        final Player playMP3 = new Player(fis);

        playMP3.play();
    }

}
