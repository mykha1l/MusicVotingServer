import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:dio/dio.dart';

class UploadSongs extends StatefulWidget {
  @override
  _UploadSongsState createState() => _UploadSongsState();
}

class _UploadSongsState extends State<UploadSongs> {
  final GlobalKey<ScaffoldState> _scaffoldstate =
      new GlobalKey<ScaffoldState>();
  String songName = 'No file choosen';
  String songSize = '0';
  PlatformFile file;

  void _getDocuments() async {
    FilePickerResult result = await FilePicker.platform.pickFiles(
      type: FileType.custom,
      allowedExtensions: ['mp3'],
    );

    if (result != null) {
      file = result.files.first;
      //String name = file.name;
      songName = file.name;
      double size = double.parse((file.size / 1000).toStringAsFixed(2));
      songSize = size.toString() + ' MB';
    }

    if (!mounted) return;
    setState(() {});
  }

  Future<void> _uploadSongs(PlatformFile file) async {
    const String HOST = 'http://10.0.2.2:8080';

    try {
      final dio = Dio();

      dio.options.headers = {
        'Content-Type': 'application/x-www-form-urlencoded'
      };

      final mainFile =
          await MultipartFile.fromFile(file.path, filename: file.name);

      final formData = FormData.fromMap(
          {'file': mainFile}); // 'file' - this is an api key, can be different

      final response = await dio.post(
        HOST + '/api/v1/songs/upload',
        data: formData,
      );

      if (response.statusCode == 200) {
        String message = 'Your song has been delivered to server.';

        updateMessage(message);
      }
    } catch (err) {
      print('uploading error: $err');
    }
  }

  void updateMessage(String uploadMessage) {
    showSnakBarMsg(uploadMessage);

    setState(() {
      file = null;
      songName = 'No file choosen';
      songSize = '0';
    });
  }

  /// Method for showing snak bar message
  void showSnakBarMsg(String msg) {
    _scaffoldstate.currentState.showSnackBar(new SnackBar(
      content: new Text(msg),
      duration: Duration(seconds: 5),
      backgroundColor: Colors.blueGrey,
    ));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldstate,
      appBar: AppBar(
        title: Text('Upload Songs'),
        centerTitle: true,
        elevation: 0,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Container(
                padding: EdgeInsets.all(20),
                child: Text(
                  'You can upload mp3 audio file to the server. Maximum size for each song should be 20 MB.',
                  textAlign: TextAlign.left,
                  style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                )),
            const SizedBox(height: 30),
            RaisedButton(
              child: Text('Choose Song'),
              onPressed: _getDocuments,
            ),
            const SizedBox(height: 30),
            Container(
                padding: EdgeInsets.all(20),
                child: Row(
                  //mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Container(
                      child: Text(
                        "Song name :   ",
                        textAlign: TextAlign.left,
                        style: TextStyle(
                            fontSize: 16, fontWeight: FontWeight.bold),
                      ),
                    ),
                    Container(
                      child: Flexible(
                        child: Text(
                          songName,
                          style: TextStyle(
                              fontSize: 14, fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                  ],
                )),
            Container(
                padding: EdgeInsets.all(20),
                child: Row(
                  //mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    Container(
                      child: Text(
                        "Song size :   ",
                        textAlign: TextAlign.left,
                        style: TextStyle(
                            fontSize: 16, fontWeight: FontWeight.bold),
                      ),
                    ),
                    Container(
                      child: Text(
                        songSize,
                        style: TextStyle(
                            fontSize: 14, fontWeight: FontWeight.bold),
                      ),
                    ),
                  ],
                )),
            const SizedBox(height: 30),
            RaisedButton(
              child: Text('Send Song'),
              onPressed: () {
                if (songSize == '0') {
                  showAlertDialog2(context);
                } else if (file.size > 20000) {
                  showAlertDialog(context);
                } else if (file.size > 0 && file.size < 20000) {
                  print("File name is " + file.name);

                  try {
                    _uploadSongs(file);
                  } catch (err) {
                    print('uploading error: $err');
                  }
                }
              },
            ),
            const SizedBox(height: 30),
          ],
        ),
      ),
    );
  }
}

showAlertDialog(BuildContext context) {
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
    content: Text("You can't send more than 20 MB audio file."),
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
    content: Text("You can't send empty audio file"),
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
