package com.haw.mvsspring.model;

public class Song {

    private final String rawName;

    public Song(final String rawName) {
        this.rawName = rawName;
    }

    public String getRawName() {
        return rawName;
    }

}