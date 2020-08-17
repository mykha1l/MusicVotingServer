import 'package:flutter/material.dart';
import 'package:mvs_flutter/pages/SongList.dart';


class Song{
  String filename;
  String title;
  String artist;
  String genre;
  String album;
  String duration;

  Song({this.filename,this.title,this.artist,this.genre,this.album,this.duration});

  factory Song.fromJson(Map<String,dynamic> json) => Song(
    filename: json['filename'] ,
    title: json['title'],

    artist: json['artist'],
    genre: json['genre'],
    album: json['album'],
    duration: json['duration'],
  );

}