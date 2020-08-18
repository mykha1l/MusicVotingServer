import 'dart:async';
import 'dart:convert';

import 'package:http/http.dart';
import 'package:mvs_flutter/Model/Song.dart';

Future<List<Song>> fetchSongs() async {
  Response response = await get('http://10.194.1.191:8080/api/v1/songs');
  var songs = List<Song>();
  if (response.statusCode == 200) {
    var songsJson = json.decode(response.body);
    for (var noteJson in songsJson) {
      songs.add(Song.fromJson(noteJson));
      print(songsJson);
    }
  }
  return songs;
}

Future<List<Song>> fetchPairsOfSongs() async {
  Response response = await get('http://10.194.1.191:8080/api/v1/pairs');
  var songs = List<Song>();
  if (response.statusCode == 200) {
    List<dynamic> data = jsonDecode(response.body);
    //var songsJson = json.decode(response.body);
    //var replaced = songsJson.substring(1);
    //print(data);
    for (var noteJson in data) {
      print(Song.fromJson(noteJson));
      songs.add(Song.fromJson(noteJson));
    }
  }
  return songs;
}
