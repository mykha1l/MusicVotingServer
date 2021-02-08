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
            ButtonTheme(
              buttonColor: Colors.blue[400],
              minWidth: 200.0,
              height: 60.0,
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(20)),
              child: RaisedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/songList');
                },
                child: Text(
                  'All Songs',
                  style: TextStyle(color: Colors.white, fontSize: 17),
                ),
              ),
            ),
            const SizedBox(
              height: 30,
            ),
            ButtonTheme(
              buttonColor: Colors.blue[400],
              minWidth: 200.0,
              height: 60.0,
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(20)),
              child: RaisedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/pairsOfSongs');
                },
                child: Text(
                  'Start Voting',
                  style: TextStyle(color: Colors.white, fontSize: 17),
                ),
              ),
            ),
            const SizedBox(height: 30),
            ButtonTheme(
              buttonColor: Colors.blue[400],
              minWidth: 200.0,
              height: 60.0,
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(20)),
              child: RaisedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/uploadSongs');
                },
                child: Text(
                  'Song Upload',
                  style: TextStyle(color: Colors.white, fontSize: 17),
                ),
              ),
            ),
            const SizedBox(height: 30),
            ButtonTheme(
              buttonColor: Colors.blue[400],
              minWidth: 200.0,
              height: 60.0,
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(20)),
              child: RaisedButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/login');
                },
                child: Text(
                  'Login',
                  style: TextStyle(color: Colors.white, fontSize: 17),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
