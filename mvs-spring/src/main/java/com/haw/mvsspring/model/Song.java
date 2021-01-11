package com.haw.mvsspring.model;

import java.io.File;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String filename;
    private String title;
    private String artist;
    private String genre;
    private String album;
    private String duration;
    private byte[] albumImage;

    public Song(String filename, String title, String artist, String genre, String album, String duration,
            byte[] albumImage) {
        this.filename = filename;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.duration = duration;
        this.albumImage = albumImage;
    }

    public Song(Integer id, String filename, String title, String artist, String genre, String album, String duration,
            byte[] albumImage) {

        this.id = id;
        this.filename = filename;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.duration = duration;
        this.albumImage = albumImage;
    }
    
    public Song(final File file) throws UnsupportedTagException, InvalidDataException, IOException {
        Mp3File mp3file = new Mp3File(file);
        this.filename = file.getName();
        this.duration = String.valueOf(mp3file.getLengthInSeconds());
        
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            this.title = id3v2Tag.getTitle();
            this.artist = id3v2Tag.getArtist();
            this.album = id3v2Tag.getAlbum();
            this.genre = id3v2Tag.getGenreDescription();
            this.albumImage = id3v2Tag.getAlbumImage();
        } else if (mp3file.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            this.title = id3v1Tag.getTitle();
            this.artist = id3v1Tag.getArtist();
            this.album = id3v1Tag.getAlbum();
            this.genre = id3v1Tag.getGenreDescription();
        }        
    }

    public Integer getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public String getGenre() {
        return genre;
    }
    public String getAlbum() {
        return album;
    }
    public String getDuration() {
        return duration;
    }
    public byte[] getAlbumImage() {
        return albumImage;
    }

}
