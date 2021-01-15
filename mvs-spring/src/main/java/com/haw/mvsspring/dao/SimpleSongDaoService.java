package com.haw.mvsspring.dao;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.haw.mvsspring.model.Song;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.stereotype.Repository;

@Repository("simpleDao")
public class SimpleSongDaoService implements SongDao {

    private List<Song> DB = new ArrayList<>();

    @Override
    public int insertSong(Song song) {
        DB.add(song);
        return 0;
    }

    @Override
    public List<Song> getAllSongs() {
        return DB;
    }

    @Override
    public int init() {
        final File file = new File("../MVS-WebApp/songs");
        final FileFilter fileFilter = new WildcardFileFilter("*.mp3");
        final File[] fileList = file.listFiles(fileFilter);
        for (final File song : fileList) {
            try {
                DB.add(new Song(song));
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

    public int deleteSong(Integer id){
        return 0;
    }

    public List<Song> searchInDB(final Map<String, String> searchItems){
        return new ArrayList<Song>();
    }
}