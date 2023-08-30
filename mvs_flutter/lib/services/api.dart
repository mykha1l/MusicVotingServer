import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart';
import 'package:mvs_flutter/Model/song.dart';
import 'package:requests/requests.dart' as CustomHttp;

const String HOST = 'http://192.168.0.72:8080';

Future<List<Song>> fetchListOfSongs() async {
  final Response response = await get(Uri.parse(HOST + '/api/v1/songs'));
  var songs = <Song>[];
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
    final Response response =
        await get(Uri.parse(HOST + '/api/v1/pairs'));
    var songs = <List<Song>>[];
    if (response.statusCode == 200) {
      songs = songFromJson(response.body);
    }
    return songs;
  } catch (e) {
    return <List<Song>>[];
  }
}

Future<Response> postRequest(List<String?> votes) async {
  const String url = HOST + '/api/v1/vote';

  //encode Map to JSON
  var body = json.encode(votes);

  Response response = await post(url as Uri,
      headers: {"Content-Type": "application/json"}, body: body);

  return response;
}

Future<int> login(Map<String, String> formData) async {
  const String url = HOST + '/login';

  var response = await CustomHttp.Requests.post(url,
      headers: {"Content-Type": "application/x-www-form-urlencoded"},
      body: formData);
  response.raiseForStatus();
  dynamic status = response.statusCode;

  return status;
}

Future<dynamic> getUser() async {
  const String url = HOST + '/api/v1/user';

  var response = await CustomHttp.Requests.get(url);
  response.raiseForStatus();
  dynamic content = response.json()['username'];

  return content;
}
