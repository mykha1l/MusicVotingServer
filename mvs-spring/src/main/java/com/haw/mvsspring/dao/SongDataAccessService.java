package com.haw.mvsspring.dao;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.haw.mvsspring.Exceptions.MyDatabaseException;
import com.haw.mvsspring.Exceptions.WrongDataException;
import com.haw.mvsspring.model.Song;
import com.haw.mvsspring.sqlite.SongRowMapper;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("sqlite")
public class SongDataAccessService implements SongDao {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SongDataAccessService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int insertSong(Song song) {
        final String sql = "INSERT INTO song (filename, title, artist, genre, album, duration, album_image) VALUES(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] { song.getFilename(), song.getTitle(), song.getArtist(), song.getGenre(),
                song.getAlbum(), song.getDuration(), song.getAlbumImage() });
        return 0;
    }

    @Override
    public List<Song> getAllSongs() {
        final String sql = "SELECT * FROM song";
        try {
            var result = jdbcTemplate.query(sql, (resultSet, i) -> {
                return new Song(resultSet.getInt("id"), resultSet.getString("filename"), resultSet.getString("title"),
                        resultSet.getString("artist"), resultSet.getString("genre"), resultSet.getString("album"),
                        resultSet.getString("duration"), resultSet.getBytes("album_image"));
            });
            if (result.isEmpty()) {
                throw new MyDatabaseException("Database is empty.");
            }
            return result;
        } catch (DataAccessException ex) {
            throw new MyDatabaseException(ex);
        }
    }

    public int removeAll() {
        final String sql = "DELETE FROM song";
        jdbcTemplate.execute(sql);
        return 0;
    }

    private boolean exists(final int id) {
        final String sql = "SELECT COUNT(*) FROM song WHERE id=" + id;
        final int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count == 1;
    }

    public int deleteSong(Integer id) {
        try {
            if (!exists(id)) {
                throw new WrongDataException("There is no song with id : " + id);
            }
            final String sql = "DELETE FROM song WHERE id=?";
            jdbcTemplate.update(sql, new Object[] { id });
        } catch (DataAccessException ex) {
            throw new MyDatabaseException(ex);
        }
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

    public List<Song> searchInDB(final Map<String, String> searchItems) {

        final var keySet = searchItems.keySet();
        final String key = (String) keySet.toArray()[0];
        final String value = searchItems.get(key);

        String sql;
        List<Song> result = new ArrayList<>();
        switch (key) {
            case "artist":
                sql = "SELECT * FROM song WHERE artist = ? ";
                result = jdbcTemplate.query(sql, new Object[] { value }, new SongRowMapper());
                break;
            case "album":
                sql = "SELECT * FROM song WHERE album= ? ";
                result = jdbcTemplate.query(sql, new Object[] { value }, new SongRowMapper());
                break;
            case "duration":
                sql = "SELECT * FROM song WHERE duration = ? ";
                result = jdbcTemplate.query(sql, new Object[] { value }, new SongRowMapper());
                break;
            case "filename":
                sql = "SELECT * FROM song WHERE filename = ? ";
                result = jdbcTemplate.query(sql, new Object[] { value }, new SongRowMapper());
                break;
            case "genre":
                sql = "SELECT * FROM song WHERE genre = ? ";
                result = jdbcTemplate.query(sql, new Object[] { value }, new SongRowMapper());
                break;
            case "title":
                sql = "SELECT * FROM song WHERE title = ? ";
                result = jdbcTemplate.query(sql, new Object[] { value }, new SongRowMapper());
                break;
            case "search":
                System.out.println(value);
                sql = "SELECT * FROM song WHERE  artist LIKE :value OR album LIKE :value OR duration LIKE :value OR filename LIKE :value OR genre LIKE :value OR title LIKE :value ";
                final SqlParameterSource namedParameters = new MapSqlParameterSource("value", "%" + value + "%");
                result = namedParameterJdbcTemplate.query(sql, namedParameters, new SongRowMapper());
                break;
        }

        return result;
    }

}
 