import 'package:masung_flutter/masung_flutter_method_channel.dart';

/// A class that provides methods for interacting with the Masung Flutter plugin.
class MasungFlutter {
  final MethodChannelMasungFlutter _channel = MethodChannelMasungFlutter();

  /// Retrieves the platform version.
  ///
  /// Returns a [Future] that completes with a [String] representing the platform version.
  Future<String?> getPlatformVersion() {
    return _channel.getPlatformVersion();
  }

  /// Prints the specified [text] to the printer.
  ///
  /// Returns a [Future] that completes with a [bool] indicating whether the printing was successful.
  Future<bool?> printString(String text) {
    return _channel.printString(text);
  }

  /// Cuts the paper in the printer.
  ///
  /// Returns a [Future] that completes with a [bool] indicating whether the paper cutting was successful.
  Future<bool?> cutPaper() {
    return _channel.cutPaper();
  }
}
