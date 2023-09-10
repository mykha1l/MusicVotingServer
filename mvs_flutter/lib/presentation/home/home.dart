import 'package:flutter/material.dart';

import 'package:mvs_flutter/utils/constants/strings.dart';
import 'package:mvs_flutter/presentation/pair_of_songs/screen.dart';
import 'package:mvs_flutter/presentation/song_list/song_list.dart';
import '../upload/upload_songs.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int _selectedIndex = 0;

  List<Widget> _pages = <Widget>[
    SongsList(),
    PairsOfSongs(),
    UploadSongs(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).backgroundColor,
      bottomNavigationBar: BottomNavigationBar(
        backgroundColor: Theme.of(context).primaryColor,
        currentIndex: _selectedIndex,
        onTap: _onItemTapped,
        selectedFontSize: 13,
        selectedIconTheme: IconThemeData(
          color: Theme.of(context).primaryColorLight,
          size: 25,
        ),
        selectedItemColor: Theme.of(context).primaryColorLight,
        selectedLabelStyle: TextStyle(
          fontWeight: FontWeight.bold,
        ),
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.music_note),
            label: 'Songs',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.favorite_border),
            label: 'Favorite',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.upload),
            label: 'Upload',
          ),
        ],
      ),
      appBar: new AppBar(
        automaticallyImplyLeading: false,
        title: Text(
          Strings.votingSystem,
          style: Theme.of(context).textTheme.headlineSmall,
        ),
        centerTitle: true,
      ),
      body: IndexedStack(
        index: _selectedIndex,
        children: _pages,
      ),
    );
  }
}
