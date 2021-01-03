import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/Song.dart';
import 'package:mvs_flutter/services/ApiExamples.dart';

import 'package:mvs_flutter/pages/PairsOfSongsPage.dart';

class PairsOfSongs extends StatefulWidget {
  @override
  _PairsOfSongsState createState() => _PairsOfSongsState();
}

class _PairsOfSongsState extends State<PairsOfSongs> {
  List<List<Song>> _songs = List<List<Song>>();
  List<Song> addedSongs = new List<Song>();
  var song;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: new AppBar(
        title: Text('Voting Panel'),
        centerTitle: true,
        elevation: 0,
      ),
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
