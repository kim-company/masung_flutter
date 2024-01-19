import 'package:masung_flutter/masung_flutter_method_channel.dart';

class MasungFlutter {
  final MethodChannelMasungFlutter _channel = MethodChannelMasungFlutter();
  Future<String?> getPlatformVersion() {
    return _channel.getPlatformVersion();
  }

  Future<bool?> printString(String text) {
    return _channel.printString(text);
  }

  Future<bool?> cutPaper() {
    return _channel.cutPaper();
  }
}
