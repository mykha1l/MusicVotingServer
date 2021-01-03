import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/Song.dart';
import 'package:mvs_flutter/services/ApiExamples.dart';
import 'dart:convert';
import 'dart:typed_data';

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
          String artist = '${index + 1}. Artist: ';
          String newSong = 'Song: ';
          String duration =
              (int.parse(_songs[index].duration) / 60).toStringAsFixed(2);
          String image64 = _songs[index].albumImage == null
              ? 'No data'
              : _songs[index].albumImage;
          Uint8List bytes;
          if (_songs[index].albumImage != null) {
            bytes = base64.decode(image64);
          }
          return Card(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15),
            ),
            color: Colors.grey[200],
            elevation: 4,
            margin: EdgeInsets.all(10),
            child: Column(
              children: <Widget>[
                Stack(
                  children: <Widget>[
                    ClipRRect(
                      borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(15),
                        topRight: Radius.circular(15),
                      ),
                      child: Image(
                        image: image64 == 'No data'
                            ? AssetImage('assets/images/no-image.png')
                            : MemoryImage(bytes),
                        height: 180,
                        width: double.infinity,
                        fit: BoxFit.fitWidth,
                      ),
                    ),
                    Positioned(
                      bottom: 20,
                      right: 10,
                      child: Container(
                        width: 230,
                        color: Colors.white60,
                        padding: EdgeInsets.symmetric(
                          vertical: 5,
                          horizontal: 20,
                        ),
                        child: Column(
                          children: [
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: [
                                Text(artist,
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontSize: 14,
                                        fontWeight: FontWeight.bold)),
                                Text(
                                  _songs[index].artist == null
                                      ? 'No Artist data'
                                      : _songs[index].artist,
                                  style: TextStyle(
                                      fontSize: 14,
                                      fontWeight: FontWeight.bold),
                                ),
                              ],
                            ),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: [
                                Text(newSong,
                                    style: TextStyle(
                                        fontSize: 14,
                                        fontWeight: FontWeight.bold)),
                                Flexible(
                                  child: Text(
                                    _songs[index].title == null
                                        ? withUpperCase
                                        : _songs[index].title,
                                    style: TextStyle(
                                        fontSize: 14,
                                        fontWeight: FontWeight.bold),
                                    maxLines: 1,
                                    softWrap: false,
                                    overflow: TextOverflow.clip,
                                  ),
                                ),
                              ],
                            ),
                          ],
                        ),
                      ),
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.all(20),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: <Widget>[
                      Row(
                        children: <Widget>[
                          Icon(
                            Icons.schedule,
                          ),
                          SizedBox(
                            width: 3,
                          ),
                          Text('$duration min'),
                        ],
                      ),
                      SizedBox(
                        width: 6,
                      ),
                      Row(
                        children: <Widget>[
                          Text(
                            'Genre:',
                            style: TextStyle(
                              fontSize: 14,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          SizedBox(
                            width: 3,
                          ),
                          Text(
                            _songs[index].genre == null
                                ? 'No data'
                                : _songs[index].genre,
                            maxLines: 1,
                            softWrap: false,
                            overflow: TextOverflow.clip,
                          )
                        ],
                      ),
                      SizedBox(
                        width: 3,
                      ),
                      Row(
                        children: <Widget>[
                          Text(
                            'Album:',
                            style: TextStyle(
                              fontSize: 14,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          SizedBox(
                            width: 3,
                          ),
                          Text(
                            _songs[index].album == null
                                ? 'No data'
                                : _songs[index].album,
                            maxLines: 1,
                            softWrap: false,
                            overflow: TextOverflow.clip,
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ],
            ),
          );
        },
        itemCount: _songs.length,
      ),
    );
  }
}
