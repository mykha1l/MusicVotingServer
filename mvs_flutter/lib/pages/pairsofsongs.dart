import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/Song.dart';
import 'package:mvs_flutter/services/apiexamples.dart';

class PairsOfSongs extends StatefulWidget {
  @override
  _PairsOfSongsState createState() => _PairsOfSongsState();
}

class _PairsOfSongsState extends State<PairsOfSongs> {
  List<List<Song>> _songs = List<List<Song>>();

  @override
  void initState() {
    super.initState();
    getSongs().then((value) {
      setState(() {
        _songs.addAll(value);
        print(_songs);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: new AppBar(
        title: Text('Voting Panel'),
        centerTitle: true,
        elevation: 0,
      ),
      body: ListView.builder(
        itemBuilder: (context, index) {
          List<Song> song2 = _songs[index];
          //Below variables are for deleting mp3 in the end of String  and capitalize first Letter
          var myString = song2[0].filename;
          var withoutMp3 = myString.replaceAll(RegExp('.mp3'), '');
          var withUpperCase =
              withoutMp3[0].toUpperCase() + withoutMp3.substring(1);
          var myString2 = song2[1].filename;
          var withoutMp32 = myString2.replaceAll(RegExp('.mp3'), '');
          var withUpperCase2 =
              withoutMp32[1].toUpperCase() + withoutMp32.substring(1);
          String artist1 = '1.Artist ';
          String artist2 = '2.Artist ';
          String song = 'Song ';
          return Card(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                children: <Widget>[
                  Text(artist1),
                  Text(
                    song2[0].artist == null
                        ? 'No Artist data'
                        : song2[0].artist,
                    style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
                  ),
                  Text(song),
                  Text(
                    song2[0].title == null ? withUpperCase : song2[0].title,
                    style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 30),
                  Text(artist2),
                  Text(
                    song2[1].artist == null
                        ? 'No Artist data'
                        : song2[1].artist,
                    style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
                  ),
                  Text(song),
                  Text(
                    song2[1].title == null ? withUpperCase2 : song2[1].title,
                    style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
                  ),
                ],
              ),
            ),
          );
        },
        itemCount: _songs.length,
      ),
    );
  }
}
