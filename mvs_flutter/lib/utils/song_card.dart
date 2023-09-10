import 'dart:typed_data';
import 'package:flutter/material.dart';
import 'package:mvs_flutter/utils/constants/strings.dart';
import 'package:mvs_flutter/Model/song.dart';

class SongCard extends StatefulWidget {
  final List<Song> addedSongs;
  final String? image64;
  final Uint8List? bytes;
  final String? artistIndex;
  final int? index;
  final String? alternativeTitle;
  final String? duration;

  SongCard({
    required this.addedSongs,
    this.image64,
    this.bytes,
    this.artistIndex,
    this.index,
    this.alternativeTitle,
    this.duration,
  });

  @override
  _SongCardState createState() => _SongCardState();
}

class _SongCardState extends State<SongCard> {
  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(15),
      ),
      color: Theme.of(context).primaryColorLight,
      elevation: 4,
      margin: EdgeInsets.all(10),
      child: Column(
        children: <Widget>[
          Stack(
            children: <Widget>[
              ClipRRect(
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(15),
                  topRight: Radius.circular(15),
                ),
                child: Image(
                  image: widget.image64 == Strings.noData
                      ? AssetImage('assets/images/no-image.png')
                      : MemoryImage(widget.bytes!) as ImageProvider,
                  height: 180,
                  width: double.infinity,
                  fit: BoxFit.fitWidth,
                ),
              ),
              Positioned(
                bottom: 20,
                right: 10,
                child: Container(
                  width: 280,
                  color: Theme.of(context).cardColor,
                  padding: EdgeInsets.symmetric(
                    vertical: 5,
                    horizontal: 20,
                  ),
                  child: Column(
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: [
                          Text(
                            widget.artistIndex!,
                            style: Theme.of(context)
                                .textTheme
                                .displaySmall
                                ?.copyWith(
                                  color: Colors.black,
                                ),
                          ),
                          Text(
                            widget.addedSongs[widget.index!].artist == null
                                ? Strings.noArtistData
                                : widget.addedSongs[widget.index!].artist!,
                            style: Theme.of(context)
                                .textTheme
                                .displaySmall
                                ?.copyWith(
                                  color: Colors.black,
                                ),
                          ),
                        ],
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: [
                          Text(
                            Strings.newSong,
                            style: Theme.of(context)
                                .textTheme
                                .displaySmall
                                ?.copyWith(
                                  color: Colors.black,
                                ),
                          ),
                          Flexible(
                            child: Text(
                              widget.addedSongs[widget.index!].title == null
                                  ? widget.alternativeTitle!
                                  : widget.addedSongs[widget.index!].title!,
                              style: Theme.of(context)
                                  .textTheme
                                  .displaySmall
                                  ?.copyWith(
                                    color: Colors.black,
                                  ),
                              maxLines: 1,
                              softWrap: false,
                              overflow: TextOverflow.clip,
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              )
            ],
          ),
          Padding(
            padding: EdgeInsets.all(20),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                Row(
                  children: <Widget>[
                    Icon(
                      Icons.schedule,
                    ),
                    SizedBox(
                      width: 3,
                    ),
                    Text(
                      '${widget.duration} min',
                      style: Theme.of(context).textTheme.displaySmall?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                  ],
                ),
                SizedBox(
                  width: 6,
                ),
                Row(
                  children: <Widget>[
                    Text(
                      Strings.album,
                      style: Theme.of(context).textTheme.displaySmall?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                    SizedBox(
                      width: 3,
                    ),
                    Text(
                      widget.addedSongs[widget.index!].genre == null
                          ? Strings.noData
                          : widget.addedSongs[widget.index!].genre!,
                      maxLines: 1,
                      softWrap: false,
                      overflow: TextOverflow.clip,
                      style: Theme.of(context).textTheme.displaySmall?.copyWith(
                            color: Colors.black,
                          ),
                    )
                  ],
                ),
                SizedBox(
                  width: 3,
                ),
                Row(
                  children: <Widget>[
                    Text(
                      Strings.album,
                      style: Theme.of(context).textTheme.displaySmall?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                    SizedBox(
                      width: 3,
                    ),
                    Text(
                      widget.addedSongs[widget.index!].album == null
                          ? Strings.noData
                          : widget.addedSongs[widget.index!].album!,
                      maxLines: 1,
                      softWrap: false,
                      overflow: TextOverflow.clip,
                      style: Theme.of(context).textTheme.displaySmall?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
