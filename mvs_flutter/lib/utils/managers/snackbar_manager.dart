import 'package:flutter/material.dart';

import 'package:mvs_flutter/utils/managers/snackbars.dart';

class SnackBarManager {
  static void showError(
      BuildContext context, {
        required String content,
      }) {
    ScaffoldMessenger.of(context).showSnackBar(
      FailSnackBar(
        text: content,
        context: context,
      ),
    );
  }

  static void showSuccess(
      BuildContext context, {
        required String content,
      }) {
    ScaffoldMessenger.of(context).showSnackBar(
      SuccessSnackBar(
        text: content,
        context: context,
      ),
    );
  }
}