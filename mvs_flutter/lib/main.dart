import 'package:flutter/material.dart';
import 'package:mvs_flutter/pages/SongList.dart';
import 'package:mvs_flutter/pages/Home.dart';
import 'package:mvs_flutter/pages/PairsOfSongs.dart';
import 'package:mvs_flutter/pages/UploadSongs.dart';

void main() {
  //SharedPreferences.setMockInitialValues({});
  runApp(MaterialApp(
    initialRoute: '/home',
    home: Home(),
    routes: {
      '/home': (context) => Home(),
      '/songList': (context) => SongList(),
      '/pairsOfSongs': (context) => PairsOfSongs(),
      '/uploadSongs': (context) => UploadSongs(),
    },
  ));
}
