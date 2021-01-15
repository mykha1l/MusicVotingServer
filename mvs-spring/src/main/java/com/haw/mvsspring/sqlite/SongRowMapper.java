package com.haw.mvsspring.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.haw.mvsspring.model.Song;

import org.springframework.jdbc.core.RowMapper;

public class SongRowMapper implements RowMapper<Song> {

    @Override
    public Song mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Song song = new Song(resultSet.getInt("id"), resultSet.getString("filename"), resultSet.getString("title"),
                resultSet.getString("artist"), resultSet.getString("genre"), resultSet.getString("album"),
                resultSet.getString("duration"), resultSet.getBytes("album_image"));
        return song;
    }

}
