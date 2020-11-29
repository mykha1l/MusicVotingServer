import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart';
import 'package:mvs_flutter/Model/Song.dart';

const String HOST = 'http://10.0.2.2:8080';

Future<List<Song>> fetchListOfSongs() async {
  final Response response = await get(HOST + '/api/v1/songs');
  var songs = List<Song>();
  if (response.statusCode == 200) {
    var songsJson = json.decode(response.body);
    for (var noteJson in songsJson) {
      songs.add(Song.fromJson(noteJson));
    }
  }
  return songs;
}

Future<List<List<Song>>> fetchPairOfSongs() async {
  try {
    final Response response = await get(HOST + '/api/v1/pairs');
    var songs = List<List<Song>>();
    if (response.statusCode == 200) {
      songs = songFromJson(response.body);
    }
    return songs;
  } catch (e) {
    return List<List<Song>>();
  }
}

Future<Response> postRequest(List<String> votes) async {
  const String url = HOST + '/api/v1/vote';

  //encode Map to JSON
  var body = json.encode(votes);

  Response response = await post(url,
      headers: {"Content-Type": "application/json"}, body: body);

  return response;
}
