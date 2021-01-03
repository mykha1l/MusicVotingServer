import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/Song.dart';
import 'dart:convert';
import 'dart:typed_data';
import 'package:mvs_flutter/services/ApiExamples.dart';

class PairsOfSongsPage extends StatefulWidget {
  final List<Song> addedSongs;
  final List<String> votes = [];

  PairsOfSongsPage(this.addedSongs);

  @override
  _PairsOfSongsPageState createState() => _PairsOfSongsPageState();
}

class _PairsOfSongsPageState extends State<PairsOfSongsPage> {
  int firstNumber = 0;
  int secondNumber = 1;
  List<bool> likedList;
  Color _colorContainer = Colors.cyan[100];

  void fillList() {
    if (likedList?.isEmpty ?? true) {
      likedList = new List<bool>.generate(10, (i) {
        return false;
      });
    }
  }

  void addNumber() {
    setState(() {
      firstNumber = firstNumber + 2;
      secondNumber = secondNumber + 2;
    });
  }

  void subtractionNumber() {
    setState(() {
      firstNumber = firstNumber - 2;
      secondNumber = secondNumber - 2;
    });
  }

  showAlertDialog(BuildContext context) {
    // set up the buttons
    Widget cancelButton = FlatButton(
      child: Text("Cancel"),
      onPressed: () {
        Navigator.of(context, rootNavigator: true).pop();
      },
    );
    Widget continueButton = FlatButton(
      child: Text("Send"),
      onPressed: () {
        postRequest(widget.votes);
        Navigator.pushNamed(context, '/home');
      },
    );

    // set up the AlertDialog
    AlertDialog alert = AlertDialog(
      title: Text("AlertDialog"),
      content: Text(
          "You have chosen all songs in list. Please press send button for sending."),
      actions: [
        cancelButton,
        continueButton,
      ],
    );

    // show the dialog
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    List<Song> newAddedSongs = widget.addedSongs;
    fillList();
    print('length is ${widget.addedSongs.length}');
    var myString = newAddedSongs[0].filename;
    var withoutMp3 = myString.replaceAll(RegExp('.mp3'), '');
    var withUpperCase = withoutMp3[0].toUpperCase() + withoutMp3.substring(1);
    var myString2 = newAddedSongs[1].filename;
    var withoutMp32 = myString2.replaceAll(RegExp('.mp3'), '');
    var withUpperCase2 =
        withoutMp32[1].toUpperCase() + withoutMp32.substring(1);

    String newSong = 'Song: ';
    String firstArtist = '1. Artist: ';
    String secondArtist = '2. Artist ';
    String firstImage = newAddedSongs[firstNumber].albumImage == null
        ? 'No data'
        : newAddedSongs[firstNumber].albumImage;
    String secondImage = newAddedSongs[secondNumber].albumImage == null
        ? 'No data'
        : newAddedSongs[secondNumber].albumImage;
    Uint8List bytes;
    if (newAddedSongs[firstNumber].albumImage != null) {
      bytes = base64.decode(firstImage);
    } else if (newAddedSongs[secondNumber].albumImage != null) {
      bytes = base64.decode(secondImage);
    }
    return Scaffold(
        body: Container(
          padding: EdgeInsets.only(top: 5.0, left: 10.0),
          margin: const EdgeInsets.all(10.0),
          child: Column(children: <Widget>[
            Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15),
              ),
              color: Colors.grey[200],
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
                          bottomLeft: Radius.circular(15),
                          bottomRight: Radius.circular(15),
                        ),
                        child: Image(
                          image: firstImage == 'No data'
                              ? AssetImage('assets/images/no-image.png')
                              : MemoryImage(bytes),
                          height: 150,
                          width: double.infinity,
                          fit: BoxFit.cover,
                        ),
                      ),
                      Positioned(
                        top: 30,
                        right: 30,
                        child: IconButton(
                          icon: Icon(
                            Icons.favorite,
                            size: 60,
                          ),
                          color: likedList[firstNumber]
                              ? Colors.red[600]
                              : Colors.red[50],
                          iconSize: 40.0,
                          onPressed: () {
                            if (likedList[secondNumber] == false) {
                              setState(() {
                                likedList[firstNumber] =
                                    !likedList[firstNumber];
                                if (likedList[firstNumber] == true) {
                                  widget.votes
                                      .add(newAddedSongs[firstNumber].filename);
                                  print(widget.votes);
                                  print(jsonEncode(widget.votes));
                                } else {
                                  widget.votes.remove(
                                      newAddedSongs[firstNumber].filename);
                                }
                              });
                            } else {
                              showAlertDialog3(context);
                            }
                          },
                        ),
                      ),
                      Positioned(
                        bottom: 10,
                        right: 10,
                        child: Container(
                          width: 230,
                          color: Colors.white60,
                          padding: EdgeInsets.symmetric(
                            vertical: 5,
                            horizontal: 20,
                          ),
                          child: Column(
                            children: [
                              Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                children: [
                                  Text(firstArtist,
                                      style: TextStyle(
                                          color: Colors.black,
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold)),
                                  Text(
                                    newAddedSongs[firstNumber].artist == null
                                        ? 'No Artist data'
                                        : newAddedSongs[firstNumber].artist,
                                    style: TextStyle(
                                        fontSize: 14,
                                        fontWeight: FontWeight.bold),
                                  ),
                                ],
                              ),
                              Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                children: [
                                  Text(newSong,
                                      style: TextStyle(
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold)),
                                  Flexible(
                                    child: Text(
                                      newAddedSongs[firstNumber].title == null
                                          ? withUpperCase
                                          : newAddedSongs[firstNumber].title,
                                      style: TextStyle(
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold),
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
                ],
              ),
            ),
            const SizedBox(height: 10),
            Card(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15),
              ),
              color: Colors.grey[200],
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
                          bottomLeft: Radius.circular(15),
                          bottomRight: Radius.circular(15),
                        ),
                        child: Image(
                          image: secondImage == 'No data'
                              ? AssetImage('assets/images/no-image.png')
                              : MemoryImage(bytes),
                          height: 150,
                          width: double.infinity,
                          fit: BoxFit.cover,
                        ),
                      ),
                      Positioned(
                        top: 30,
                        right: 30,
                        child: IconButton(
                          icon: Icon(
                            Icons.favorite,
                            size: 60,
                          ),
                          color: likedList[secondNumber]
                              ? Colors.red[600]
                              : Colors.black54,
                          iconSize: 40.0,
                          onPressed: () {
                            if (likedList[firstNumber] == false) {
                              setState(() {
                                //print('You pressed left button');
                                likedList[secondNumber] =
                                    !likedList[secondNumber];
                                if (likedList[secondNumber] == true) {
                                  widget.votes.add(
                                      newAddedSongs[secondNumber].filename);
                                } else {
                                  widget.votes.remove(
                                      newAddedSongs[secondNumber].filename);
                                }
                              });
                            } else {
                              showAlertDialog3(context);
                            }
                          },
                        ),
                      ),
                      Positioned(
                        bottom: 10,
                        right: 10,
                        child: Container(
                          width: 230,
                          color: Colors.white60,
                          padding: EdgeInsets.symmetric(
                            vertical: 5,
                            horizontal: 20,
                          ),
                          child: Column(
                            children: [
                              Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                children: [
                                  Text(secondArtist,
                                      style: TextStyle(
                                          color: Colors.black,
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold)),
                                  Text(
                                    newAddedSongs[secondNumber].artist == null
                                        ? 'No Artist data'
                                        : newAddedSongs[secondNumber].artist,
                                    style: TextStyle(
                                        fontSize: 14,
                                        fontWeight: FontWeight.bold),
                                  ),
                                ],
                              ),
                              Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                children: [
                                  Text(newSong,
                                      style: TextStyle(
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold)),
                                  Flexible(
                                    child: Text(
                                      newAddedSongs[secondNumber].title == null
                                          ? withUpperCase
                                          : newAddedSongs[secondNumber].title,
                                      style: TextStyle(
                                          fontSize: 14,
                                          fontWeight: FontWeight.bold),
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
                ],
              ),
            ),
            const SizedBox(height: 10),
            Text(
              'Please choose 1 of these 2 songs with liking it and press next button for further pairs. In total there is ${widget.addedSongs.length} songs.',
              style: TextStyle(
                fontSize: 14.0,
                color: Colors.red[400],
              ),
            ),
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
                  //print('You pressed left button');

                  if (firstNumber > 0) {
                    subtractionNumber();
                  } else if (firstNumber == 0) {
                    showAlertDialog2(context);
                  }
                },
                child: Icon(Icons.navigate_before),
              ),
              FloatingActionButton(
                heroTag: "btn2",
                onPressed: () {
                  //print('You pressed right button');

                  if (firstNumber < 8) {
                    addNumber();
                  } else {
                    showAlertDialog(context);
                  }
                },
                child: Icon(Icons.navigate_next),
              )
            ],
          ),
        ));
  }
}

showAlertDialog2(BuildContext context) {
  // set up the button
  Widget okButton = FlatButton(
    child: Text("OK"),
    onPressed: () {
      Navigator.of(context, rootNavigator: true).pop();
    },
  );

  // set up the AlertDialog
  AlertDialog alert = AlertDialog(
    title: Text("Alert"),
    content: Text(
        "This is a first step.You can't go back. Please press next button for further pairs."),
    actions: [
      okButton,
    ],
  );

  // show the dialog
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return alert;
    },
  );
}

showAlertDialog3(BuildContext context) {
  // set up the button
  Widget okButton = FlatButton(
    child: Text("OK"),
    onPressed: () {
      Navigator.of(context, rootNavigator: true).pop();
    },
  );

  // set up the AlertDialog
  AlertDialog alert = AlertDialog(
    title: Text("Alert"),
    content: Text("You can only choose 1 song for favorite from this pairs"),
    actions: [
      okButton,
    ],
  );

  // show the dialog
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return alert;
    },
  );
}
