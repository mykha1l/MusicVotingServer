package com.haw.mvsspring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.haw.mvsspring.dao.SongDao;
import com.haw.mvsspring.model.Song;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private final SongDao songDao;

    @Autowired
    private VotesHandler votesHandler;

    @Autowired
    public SongService(@Qualifier("sqlite") SongDao songDao)
            throws UnsupportedTagException, InvalidDataException, IOException {
        songDao.init();
        this.songDao = songDao;
    }

    public int addSong(Song song) {
        return songDao.insertSong(song);
    }

    public List<Song> getAllSongs() {
        return songDao.getAllSongs();
    }

    public List<Song[]> getSongPairs() {
        
        final List<Song> filteredVotes = getFilteredAlreadyVoted(getAllSongs());
        
        makeEvenSongList(filteredVotes);
        return getPairsList(filteredVotes);
    }

    private List<Song> getFilteredAlreadyVoted(List<Song> songs) {
        ArrayList<Song> filteredVotes = new ArrayList<>();

        for (Song el : songs) {

            if (!votesHandler.mostlyVoted.contains(el.getFilename())) {
                filteredVotes.add(el);
            }
        }
        return filteredVotes;
        }

    private void makeEvenSongList(List<Song> filteredVotes) {
        if ((filteredVotes.size() % 2) != 0) {
            filteredVotes.remove(filteredVotes.size() - 1);
        }
    }

    private List<Song[]> getPairsList(List<Song> songList) {

        final List<Song[]> votingList = new ArrayList<>();
        for (int i = 0; i < songList.size(); i = i + 2) {
            final Song[] pair = new Song[2];
            pair[0] = songList.get(i);
            pair[1] = songList.get(i + 1);
            votingList.add(pair);
        }

        return votingList;
    }

    public int deleteSong(Integer id) {
        songDao.deleteSong(id);
        return 0;
    }
    
    public List<Song> searchInDB(final Map<String, String> searchItems){
        return songDao.searchInDB(searchItems);
    }

    public void changeSongInfo( Integer id, Map<String, String> body){
        songDao.changeSongInfo(id, body);
    }

}
