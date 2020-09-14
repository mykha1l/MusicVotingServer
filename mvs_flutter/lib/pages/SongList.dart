import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/Song.dart';
import 'package:mvs_flutter/services/ApiExamples.dart';

class SongList extends StatefulWidget {
  @override
  _SongListState createState() => _SongListState();
}

class _SongListState extends State<SongList> {
  List<Song> _songs = List<Song>();

  @override
  void initState() {
    super.initState();
    fetchListOfSongs().then((value) {
      setState(() {
        _songs.addAll(value);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: new AppBar(
        title: Text('All Songs'),
        centerTitle: true,
        elevation: 0,
      ),
      body: ListView.builder(
        itemBuilder: (context, index) {
          //Below variables are for deleting mp3 in the end of String  and capitalize first Letter
          var myString = _songs[index].filename;
          var withoutMp3 = myString.replaceAll(RegExp('.mp3'), '');
          var withUpperCase =
              withoutMp3[0].toUpperCase() + withoutMp3.substring(1);
          return Card(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                children: <Widget>[
                  Text(
                    _songs[index].artist == null ? '' : _songs[index].artist,
                    style: TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
                  ),
                  Text(
                    _songs[index].title == null
                        ? withUpperCase
                        : _songs[index].title,
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
