package com.haw.mvsspring.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class VotesHandler {

    public  int votersNumber = 2;
    public  int playlistSize = 3;

    public final ArrayList<List<String>> votes = new ArrayList<>();
    public ArrayList<String> mostlyVoted = new ArrayList<>();
    public final ArrayList<String> sessionIDs = new ArrayList<>();

    public ArrayList<String> calculateMostlyVotedSongs() {
        final Map<String, Integer> songMap = createSongMap(votes);
        final Map<String, Integer> sortedSongMap = sortSongMap(songMap);
        mostlyVoted = getTopSongs(sortedSongMap);
        return mostlyVoted;
    }

    public Map<String, Integer> createSongMap(final ArrayList<List<String>> votesArray) {
        final Map<String, Integer> songMap = new HashMap<>();
        for (final List<String> list : votes) {
            for (final String name : list) {
                final Integer count = songMap.get(name);
                if (count == null) {
                    songMap.put(name, 1);
                } else {
                    songMap.put(name, count + 1);
                }
            }
        }

        return songMap;
    }

    public void refreshSessionList() {
        sessionIDs.clear();
    }

    public Map<String, Integer> sortSongMap(final Map<String, Integer> songMap) {
        return songMap.entrySet().stream().sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public ArrayList<String> getTopSongs(final Map<String, Integer> sortedSongMap) {
        final ArrayList<String> result = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Integer> entry : sortedSongMap.entrySet()) {
            result.add(entry.getKey());
            i++;
            if (i == playlistSize) {
                break;
            }
        }

        return result;
    }
}
