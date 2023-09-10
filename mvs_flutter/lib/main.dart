import 'package:flutter/material.dart';
import 'package:mvs_flutter/presentation/login/login.dart';
import 'package:mvs_flutter/presentation/song_list/song_list.dart';
import 'package:mvs_flutter/presentation/home/home.dart';
import 'package:mvs_flutter/presentation/pair_of_songs/screen.dart';
import 'package:mvs_flutter/presentation/upload/upload_songs.dart';
import 'package:mvs_flutter/utils/theme.dart';

void main() {
  //SharedPreferences.setMockInitialValues({});
  runApp(
    MaterialApp(
      theme: lightTheme,
      initialRoute: '/home',
      home: Home(),
      routes: {
        '/home': (context) => Home(),
        '/songList': (context) => SongsList(),
        '/pairsOfSongs': (context) => PairsOfSongs(),
        '/uploadSongs': (context) => UploadSongs(),
        '/login': (context) => Login(),
      },
    ),
  );
}
