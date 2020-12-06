import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: new AppBar(
        automaticallyImplyLeading: false,
        title: Text('Music Voting System'),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            RaisedButton.icon(
              onPressed: () {
                Navigator.pushNamed(context, '/songList');
              },
              icon: Icon(Icons.music_note),
              label:
                  Text('List of all songs', style: TextStyle(fontSize: 18.0)),
              padding: const EdgeInsets.all(20.0),
            ),
            const SizedBox(height: 30),
            RaisedButton.icon(
              onPressed: () {
                Navigator.pushNamed(context, '/pairsOfSongs');
              },
              icon: Icon(Icons.music_note),
              label: Text('Pairs of Songs', style: TextStyle(fontSize: 18.0)),
              padding: const EdgeInsets.all(20.0),
            ),
            const SizedBox(height: 30),
            RaisedButton.icon(
              onPressed: () {
                Navigator.pushNamed(context, '/uploadSongs');
              },
              icon: Icon(Icons.music_note),
              label: Text('Song Upload', style: TextStyle(fontSize: 18.0)),
              padding: const EdgeInsets.all(20.0),
            ),
          ],
        ),
      ),
    );
  }
}
