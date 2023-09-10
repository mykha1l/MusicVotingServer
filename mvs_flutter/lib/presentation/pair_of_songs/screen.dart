import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/song.dart';
import 'package:mvs_flutter/services/api.dart';

import 'package:mvs_flutter/presentation/pair_of_songs/pair_of_songs.dart';

import '../../utils/constants/strings.dart';

class PairsOfSongs extends StatefulWidget {
  @override
  _PairsOfSongsState createState() => _PairsOfSongsState();
}

class _PairsOfSongsState extends State<PairsOfSongs> {
  List<List<Song>> _songs = <List<Song>>[];
  List<Song> addedSongs = <Song>[];
  var song;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).backgroundColor,
      body: Container(
        child: FutureBuilder<List<List<Song>>>(
          future: fetchPairOfSongs(),
          builder: (BuildContext context, AsyncSnapshot snapshot) {
            if (snapshot.hasData) {
              _songs.addAll(snapshot.data);

              for (var songs in _songs) {
                for (song in songs) {
                  addedSongs.add(song);
                }
              }
              return PairsOfSongsPage(addedSongs);
            } else if (snapshot.data == null) {
              return Container(
                child: Center(
                  child: CircularProgressIndicator(),
                ),
              );
            }

            return new CircularProgressIndicator();
          },
        ),
      ),
    );
  }
}
