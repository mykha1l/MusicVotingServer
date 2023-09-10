import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/song.dart';
import 'package:mvs_flutter/services/api.dart';
import 'package:mvs_flutter/utils/constants/strings.dart';
import 'dart:convert';
import 'dart:typed_data';
import 'package:mvs_flutter/utils/song_card.dart';

class SongsList extends StatefulWidget {
  @override
  _SongsListState createState() => _SongsListState();
}

class _SongsListState extends State<SongsList> {
  List<Song> _songs = <Song>[];

  @override
  void initState() {
    super.initState();
    fetchListOfSongs().then((value) {
      setState(() {
        _songs.addAll(value);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    getUser().then((value) => print(value));
    return Scaffold(
      backgroundColor: Theme.of(context).backgroundColor,
      body: ListView.builder(
        itemBuilder: (context, index) {
          //Below variables are for deleting mp3 in the end of String  and capitalize first Letter
          String? fileName = _songs[index].filename;
          String? withoutMp3 = fileName?.replaceAll(RegExp('.mp3'), '');
          String? alternativeTitle =
              withoutMp3![0].toUpperCase() + withoutMp3.substring(1);
          String? artistIndex = '${index + 1}. ${Strings.artist}';
          String? duration =
              (int.parse(_songs[index].duration!) / 60).toStringAsFixed(2);
          String? image64 = _songs[index].albumImage == null
              ? Strings.noData
              : _songs[index].albumImage;
          Uint8List? bytes =
              _songs[index].albumImage != null ? base64.decode(image64!) : null;
          // if (_songs[index].albumImage != null) {
          //   bytes = base64.decode(image64!);
          // }

          return SongCard(
            alternativeTitle: alternativeTitle,
            bytes: bytes,
            image64: image64,
            duration: duration,
            index: index,
            artistIndex: artistIndex,
            addedSongs: _songs,
          );
        },
        itemCount: _songs.length,
      ),
    );
  }
}
