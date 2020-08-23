import 'dart:convert';

//Below line of code is for fetching List of objects from Json
List<List<Song>> songFromJson(String str) => List<List<Song>>.from(json
    .decode(str)
    .map((x) => List<Song>.from(x.map((x) => Song.fromJson(x)))));

String song2ToJson(List<List<Song>> data) => json.encode(List<dynamic>.from(
    data.map((x) => List<dynamic>.from(x.map((x) => x.toJson())))));

class Song {
  Song({
    this.filename,
    this.title,
    this.artist,
    this.genre,
    this.album,
    this.duration,
    this.albumImage,
  });

  String filename;
  String title;
  String artist;
  String genre;
  String album;
  String duration;
  String albumImage;

  //Below function is for fetching object from Json
  factory Song.fromJson(Map<String, dynamic> json) => Song(
        filename: json["filename"],
        title: json["title"] == null ? null : json["title"],
        artist: json["artist"] == null ? null : json["artist"],
        genre: json["genre"] == null ? null : json["genre"],
        album: json["album"] == null ? null : json["album"],
        duration: json["duration"],
        albumImage: json["albumImage"] == null ? null : json["albumImage"],
      );

  Map<String, dynamic> toJson() => {
        "filename": filename,
        "title": title == null ? null : title,
        "artist": artist == null ? null : artist,
        "genre": genre == null ? null : genre,
        "album": album == null ? null : album,
        "duration": duration,
        "albumImage": albumImage == null ? null : albumImage,
      };
}
