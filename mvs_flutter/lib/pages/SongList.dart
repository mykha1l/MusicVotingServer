import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'dart:convert';

import 'package:mvs_flutter/Model/Song.dart';



class SongList extends StatefulWidget {
  @override
  _SongListState createState() => _SongListState();
}

class _SongListState extends State<SongList> {



  List<Song> _songs = List<Song>();

  Future<List<Song>> fetchSongs() async {

    Response response = await get('http://10.194.1.191:8080/api/v1/songs');

    var songs = List<Song>();

    if(response.statusCode == 200){
      var songsJson = json.decode(response.body);
      for(var noteJson in songsJson){

        songs.add(Song.fromJson(noteJson));
      }

    }


    return songs;
  }


  @override
  void initState() {
    super.initState();
    fetchSongs().then((value){
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
        title: Text('Music Voting System'),
        centerTitle: true,
        elevation: 0,
      ),
      body: ListView.builder(
      itemBuilder: (context,index){
        return Card(
          child:Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            children: <Widget>[
              Text(
                _songs[index].artist == null ? '' : _songs[index].artist,

                style: TextStyle(
                  fontSize: 12,
                  fontWeight: FontWeight.bold
                ),
              ),
              Text(
                _songs[index].title == null ? '' : _songs[index].title,

                style: TextStyle(
                    fontSize: 12,
                    fontWeight: FontWeight.bold
                ),
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
