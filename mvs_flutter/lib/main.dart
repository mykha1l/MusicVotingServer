import 'package:flutter/material.dart';
import 'package:mvs_flutter/pages/login.dart';
import 'package:mvs_flutter/pages/song_list.dart';
import 'package:mvs_flutter/pages/home.dart';
import 'package:mvs_flutter/pages/pairs_of_songs.dart';
import 'package:mvs_flutter/pages/upload_songs.dart';

void main() {
  //SharedPreferences.setMockInitialValues({});
  runApp(
    MaterialApp(
      initialRoute: '/home',
      home: Home(),
      routes: {
        '/home': (context) => Home(),
        '/songList': (context) => SongList(),
        '/pairsOfSongs': (context) => PairsOfSongs(),
        '/uploadSongs': (context) => UploadSongs(),
        '/login': (context) => Login(),
      },
    ),
  );
}
