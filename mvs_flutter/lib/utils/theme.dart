import 'package:flutter/material.dart';

ThemeData lightTheme = ThemeData(
  brightness: Brightness.light,
  useMaterial3: true,
  primaryColor: Colors.blue,
  backgroundColor: Colors.grey,
  bottomAppBarColor: Colors.blue,
  primaryColorLight: Colors.white,
  errorColor: Colors.red,
  cardColor: Colors.white60,

  textTheme: const TextTheme(
    displayLarge: TextStyle(
        fontSize: 96, fontWeight: FontWeight.w300, color: Colors.black),
    displayMedium: TextStyle(
        fontSize: 60, fontWeight: FontWeight.w400, color: Colors.black),
    displaySmall: TextStyle(
      fontSize: 14,
      fontWeight: FontWeight.bold,
    ),
    headlineMedium: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
    headlineSmall: TextStyle(color: Colors.white, fontSize: 17),
    titleLarge: TextStyle(
      color: Colors.blue,
      fontWeight: FontWeight.w500,
      fontSize: 30,
    ),
    bodyLarge: TextStyle(
        fontSize: 16, fontWeight: FontWeight.w400, color: Colors.black87),
    bodyMedium: TextStyle(
        fontSize: 14, fontWeight: FontWeight.w400, color: Colors.black87),
    bodySmall: TextStyle(
        fontSize: 12, fontWeight: FontWeight.w400, color: Colors.black54),
    labelLarge: TextStyle(
        fontSize: 14, fontWeight: FontWeight.w500, color: Colors.white),
  ),
  elevatedButtonTheme: ElevatedButtonThemeData(
    style: ElevatedButton.styleFrom(
      backgroundColor: Colors.blue[400], // background (button) color
      foregroundColor:  Colors.blue[400], // foreground (text) color
    ),
  ),
  appBarTheme: const AppBarTheme(
    color: Colors.blue,
    iconTheme: IconThemeData(
      color: Colors.white,
    ),
  ),
  cardTheme: CardTheme(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(4.0),
    ),
  ),
  colorScheme: ColorScheme.fromSeed(
    seedColor: Colors.blue,
  ),
);
