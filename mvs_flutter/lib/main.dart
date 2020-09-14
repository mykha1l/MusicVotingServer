import 'package:flutter/material.dart';
import 'package:mvs_flutter/pages/SongList.dart';
import 'package:mvs_flutter/pages/Home.dart';
import 'package:mvs_flutter/pages/PairsOfSongs.dart';

void main() => runApp(MaterialApp(
      initialRoute: '/home',
      home: Home(),
      routes: {
        '/home': (context) => Home(),
        '/songList': (context) => SongList(),
        '/pairsOfSongs': (context) => PairsOfSongs(),
      },
    ));
