package com.haw.mvsspring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

@Service
public class MyPlayer {

    public String currentSong;
    public Player player;

    @Autowired
    private VotesHandler votesHandler;

    public void play(List<String> filenames)
            throws FileNotFoundException, JavaLayerException, UnsupportedEncodingException {

        for (final String filename : filenames) {
            final File file = new File("../MVS-WebApp/songs/" + filename);
            final FileInputStream fis = new FileInputStream(file);
            player = new Player(fis);
            currentSong = filename;
            player.play(300);
            player.close();
        }
        votesHandler.refreshSessionList();
    }

    public void stop() {
        if (player != null) {
            player.close();
            votesHandler.refreshSessionList();
        }
    }

}
