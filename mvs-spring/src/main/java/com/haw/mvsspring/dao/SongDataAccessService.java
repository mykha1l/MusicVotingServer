package com.haw.mvsspring.dao;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import com.haw.mvsspring.model.Song;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("sqlite")
public class SongDataAccessService implements SongDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertSong(Song song) {
        final String sql = "INSERT INTO song (filename, title, artist, genre, album, duration, album_image) VALUES(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object [] { song.getFilename(), song.getTitle(), song.getArtist(), song.getGenre(),
        song.getAlbum(), song.getDuration(), song.getAlbumImage()} );
        return 0;
    }

    @Override
    public List<Song> getAllSongs() {
        final String sql = "SELECT * FROM song";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Song( resultSet.getString("filename"),
                    resultSet.getString("title"), resultSet.getString("artist"), resultSet.getString("genre"),
                    resultSet.getString("album"), resultSet.getString("duration"),
                    resultSet.getBytes("album_image"));
        });
    }

    public int removeAll() {
        final String sql = "DELETE FROM song";
        jdbcTemplate.execute(sql);
        return 0;
    }

    @Override
    public int init() throws UnsupportedTagException, InvalidDataException, IOException {
        removeAll();
        final File file = new File("../MVS-WebApp/songs");
        final FileFilter fileFilter = new WildcardFileFilter("*.mp3");
        final File[] fileList = file.listFiles(fileFilter);
        for (final File song : fileList) {
            try {
                insertSong(new Song(song));
            } catch (UnsupportedTagException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidDataException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return 0;
    }
    
}
