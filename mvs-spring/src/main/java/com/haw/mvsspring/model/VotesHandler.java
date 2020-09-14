package com.haw.mvsspring.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class VotesHandler {

    public static int votersNumber = 1;
    public static int playlistSize = 3;

    public static final ArrayList<List<String>> votes = new ArrayList<>();

    public static ArrayList<String> getMostlyVotedSongs() {
        final Map<String, Integer> songMap = createSongMap(votes);
        final Map<String, Integer> sortedSongMap = sortSongMap(songMap);

        return getTopSongs(sortedSongMap);
    }

    public static Map<String, Integer> createSongMap(final ArrayList<List<String>> votesArray) {
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

    public static Map<String, Integer> sortSongMap(final Map<String, Integer> songMap) {
        return songMap.entrySet().stream().sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static ArrayList<String> getTopSongs(final Map<String, Integer> sortedSongMap) {
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
