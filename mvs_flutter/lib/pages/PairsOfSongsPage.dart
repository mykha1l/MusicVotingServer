import 'package:flutter/material.dart';
import 'package:mvs_flutter/Model/Song.dart';
import 'dart:convert';
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
  Color _colorContainer1 = Colors.cyan[100];
  Color _colorContainer2 = Colors.cyan[100];
  bool firstBoolValue;
  bool secondBoolValue;

  List<bool> likedList;

  void fillList() {
    if (likedList?.isEmpty ?? true) {
      likedList = new List<bool>.generate(10, (i) {
        return false;
      });

      bool newBool = likedList[0];
      print('Boolean value is $newBool');
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
    print('Burdayam ishleyirem');

    List<Song> newAddedSongs = widget.addedSongs;

    fillList();

    // for (var i = 0; i <= 9; i++) {
    //   print(newAddedSongs[i].filename);
    // }

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
              onTap: () {},
              child: Container(
                  height: 100,
                  color: _colorContainer1,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      Column(children: <Widget>[
                        const SizedBox(height: 10),
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
                      ]),
                      IconButton(
                        icon: Icon(Icons.favorite),
                        color: likedList[firstNumber]
                            ? Colors.red[600]
                            : Colors.grey,
                        iconSize: 40.0,
                        onPressed: () {
                          if (likedList[secondNumber] == false) {
                            setState(() {
                              //print('You pressed left button');
                              likedList[firstNumber] = !likedList[firstNumber];
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
                    ],
                  )),
            ),
            const SizedBox(height: 30),
            InkWell(
              onTap: () {},
              child: Container(
                  height: 100,
                  color: _colorContainer1,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      Column(children: <Widget>[
                        const SizedBox(height: 10),
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
                              : newAddedSongs[secondNumber].title,
                          style: TextStyle(
                              fontSize: 14, fontWeight: FontWeight.bold),
                        ),
                      ]),
                      IconButton(
                        icon: Icon(Icons.favorite),
                        color: likedList[secondNumber]
                            ? Colors.red[600]
                            : Colors.grey,
                        iconSize: 40.0,
                        onPressed: () {
                          if (likedList[firstNumber] == false) {
                            setState(() {
                              //print('You pressed left button');
                              likedList[secondNumber] =
                                  !likedList[secondNumber];
                              if (likedList[secondNumber] == true) {
                                widget.votes
                                    .add(newAddedSongs[secondNumber].filename);
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
                    ],
                  )),
            ),
            const SizedBox(height: 20),
            Text(
              'Please choose 1 of this 2 songs and press next button for further pairs. In total there is 10 songs.You can choose only 5 of them for voting .',
              style: TextStyle(
                fontSize: 16.0,
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
                    _colorContainer1 = Colors.cyan[100];
                    _colorContainer2 = Colors.cyan[100];
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
        "There is not songs for listing. Please press Ok button for continue."),
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
    content: Text("You can only choose one of this pair songs"),
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
