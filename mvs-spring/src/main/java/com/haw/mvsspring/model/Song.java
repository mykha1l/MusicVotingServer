package com.haw.mvsspring.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Song {

    private String filename;
    private String title;
    private String artist;
    private String genre;
    private String album;
    private String duration;

    public Song(final File mp3File) {
        try {
            final InputStream input = new FileInputStream(mp3File);
            final ContentHandler handler = new DefaultHandler();
            final Metadata metadata = new Metadata();
            final Parser parser = new Mp3Parser();
            final ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();
            this.filename = mp3File.getName();
            this.title = metadata.get("title");
            this.artist = metadata.get("xmpDM:artist");
            this.genre = metadata.get("xmpDM:genre");
            this.album = metadata.get("xmpDM:album");
            this.duration = metadata.get("xmpDM:duration");
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final TikaException e) {
            e.printStackTrace();
        }
        
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

}
