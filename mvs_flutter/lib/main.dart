import 'package:flutter/material.dart';
import 'package:mvs_flutter/pages/SongList.dart';
import 'package:mvs_flutter/pages/home.dart';
import 'package:mvs_flutter/pages/pairsofsongs.dart';

void main() => runApp(MaterialApp(
      initialRoute: '/home',
      home: Home(),
      routes: {
        '/home': (context) => Home(),
        '/songList': (context) => SongList(),
        '/pairsOfSongs': (context) => PairsOfSongs(),
      },
    ));
