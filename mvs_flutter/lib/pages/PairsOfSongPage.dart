import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/Song.dart';

class PairsOfSongsPage extends StatefulWidget {
  final List<Song> addedSongs;

  PairsOfSongsPage(this.addedSongs);
  @override
  _PairsOfSongsPageState createState() => _PairsOfSongsPageState();
}

class _PairsOfSongsPageState extends State<PairsOfSongsPage> {
  int firstNumber = 0;
  int secondNumber = 1;

  void newState() {
    setState(() {
      firstNumber = firstNumber + 2;
      secondNumber = secondNumber + 2;
      print("New first number is $firstNumber");
      print("New second  number is $secondNumber");
    });
  }

  @override
  Widget build(BuildContext context) {
    print('Burdayam ishleyirem');

    List<Song> newAddedSongs = widget.addedSongs;
    //print('new songs $newAddedSongs');
    var myString = newAddedSongs[0].filename;
    var withoutMp3 = myString.replaceAll(RegExp('.mp3'), '');
    var withUpperCase = withoutMp3[0].toUpperCase() + withoutMp3.substring(1);
    var myString2 = newAddedSongs[1].filename;
    var withoutMp32 = myString2.replaceAll(RegExp('.mp3'), '');
    var withUpperCase2 =
        withoutMp32[1].toUpperCase() + withoutMp32.substring(1);
    String artist1 = '1.Artist ';
    String artist2 = '2.Artist ';
    String newSong = 'Song ';

    return Scaffold(
        body: Container(
          padding: EdgeInsets.only(top: 15.0, left: 10.0),

          //alignment: Alignment.center,
          //width: double.infinity,
          margin: const EdgeInsets.all(10.0),
          child: Column(children: <Widget>[
            InkWell(
                onTap: () {
                  print('First Inkwell pressed');
                },
                child: Container(
                    alignment: Alignment.topCenter,
                    height: 100,
                    color: Colors.brown,
                    child: Column(children: <Widget>[
                      Text(artist1),
                      Text(
                        newAddedSongs[firstNumber].artist == null
                            ? 'No Artist data'
                            : newAddedSongs[firstNumber].artist,
                        style: TextStyle(
                            fontSize: 14, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 10),
                      Text(newSong),
                      Text(
                        newAddedSongs[firstNumber].title == null
                            ? withUpperCase
                            : newAddedSongs[firstNumber].title,
                        style: TextStyle(
                            fontSize: 14, fontWeight: FontWeight.bold),
                      ),
                    ]))),
            const SizedBox(height: 30),
            InkWell(
                onTap: () {
                  print('Press ishleyir');
                  print('Second Inkwell pressed');
                },
                child: Container(
                    alignment: Alignment.topCenter,
                    height: 100,
                    color: Colors.cyan,
                    child: Column(children: <Widget>[
                      Text(artist2),
                      Text(
                        newAddedSongs[secondNumber].artist == null
                            ? 'No Artist data'
                            : newAddedSongs[secondNumber].artist,
                        style: TextStyle(
                            fontSize: 14, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 10),
                      Text(newSong),
                      Text(
                        newAddedSongs[secondNumber].title == null
                            ? withUpperCase2
                            : newAddedSongs[1].title,
                        style: TextStyle(
                            fontSize: 14, fontWeight: FontWeight.bold),
                      ),
                    ]))),
          ]),
        ),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
        floatingActionButton: Padding(
          padding: const EdgeInsets.all(25.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              FloatingActionButton(
                heroTag: "btn1",
                onPressed: () {
                  print('You pressed left button');
                },
                child: Icon(Icons.navigate_before),
              ),
              FloatingActionButton(
                heroTag: "btn2",
                onPressed: () {
                  print('You pressed right button');
                  newState();
                },
                child: Icon(Icons.navigate_next),
              )
            ],
          ),
        ));
  }
}
