import 'package:flutter/material.dart';

class SuccessSnackBar extends SnackBar {
  SuccessSnackBar({
    required String text,
    required BuildContext context,
  }) : super(
    content: Text(text),
    backgroundColor: Colors.blueGrey,
  );
}

class FailSnackBar extends SnackBar {
  FailSnackBar({
    required String text,
    required BuildContext context,
  }) : super(
    content: Text(text),
    backgroundColor: Theme.of(context).errorColor,
  );
}