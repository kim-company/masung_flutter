import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:masung_flutter/alignment_enum.dart';
import 'package:masung_flutter/masung_flutter.dart';
import 'package:masung_flutter/under_line_enum.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  final _masungFlutterPlugin = MasungFlutter();
  bool b = false;

  @override
  void initState() {
    super.initState();
    initPlatformState();
    printText();
  }

  Future<void> initPlatformState() async {
    String platformVersion;

    try {
      platformVersion = await _masungFlutterPlugin.getPlatformVersion() ??
          'Unknown platform version';
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  printText() {
    _masungFlutterPlugin.setFontSize(
      fontSize: 1,
    );
    _masungFlutterPlugin.setAlignment(AlignmentEnum.center);
    _masungFlutterPlugin.setFontBold(true);
    _masungFlutterPlugin.setFontUnderline(UnderLineEnum.double);
    _masungFlutterPlugin.printString('Skorpion Express', newLine: true);
    _masungFlutterPlugin.clearCache();
    _masungFlutterPlugin.setMargin(margin: 20);
    _masungFlutterPlugin.printString('Order:', newLine: true);
    _masungFlutterPlugin.printString('1234567890', newLine: true);
    _masungFlutterPlugin.printString('Date:', newLine: true);
    _masungFlutterPlugin.printString('2021-09-09', newLine: true);
    _masungFlutterPlugin.printString('Time:', newLine: true);
    _masungFlutterPlugin.feedDot(10);
    _masungFlutterPlugin.printString('09:09:09', newLine: true);
    _masungFlutterPlugin.feedLine(5);
    _masungFlutterPlugin.cutPaper(false);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: ListView(
          children: [
            Center(
              child: Text('Running on: $_platformVersion\n'),
            ),
            Text(b ? 'true' : 'false')
          ],
        ),
      ),
    );
  }
}
