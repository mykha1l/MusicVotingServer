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

  void addNumber() {
    setState(() {
      firstNumber = firstNumber + 2;
      secondNumber = secondNumber + 2;
      // print("New first number is $firstNumber");
      // print("New second  number is $secondNumber");
    });
  }

  void subtractionNumber() {
    setState(() {
      firstNumber = firstNumber - 2;
      secondNumber = secondNumber - 2;
    });
  }

  @override
  Widget build(BuildContext context) {
    print('Burdayam ishleyirem');

    List<Song> newAddedSongs = widget.addedSongs;

    String newFirstNumber = newAddedSongs[0].filename;
    String newSecondNumber = newAddedSongs[1].filename;
    String newNinthNumber = newAddedSongs[8].filename;
    String newTenthNumber = newAddedSongs[9].filename;

    print(
        '1.Filename $newFirstNumber,2.Filename $newSecondNumber,9.Filename $newNinthNumber,10.Filename $newTenthNumber ');

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
                    color: Colors.cyan[100],
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
                    color: Colors.cyan[100],
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
                            : newAddedSongs[secondNumber].title,
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
                  print('You pressed right button');

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
    onPressed: () {},
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
