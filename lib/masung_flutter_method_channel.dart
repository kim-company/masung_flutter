import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'masung_flutter_platform_interface.dart';

/// An implementation of [MasungFlutterPlatform] that uses method channels
///  to communicate with the native Android Masung printer library.
class MethodChannelMasungFlutter extends MasungFlutterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('masung_flutter');

  /// Retrieves the platform version from the native platform.
  ///
  /// Returns a [Future] that completes with a [String] representing the platform version.
  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  /// Prints the given [text] using the native platform's printing functionality.
  ///
  /// Returns a [Future] that completes with a [bool] indicating whether the printing was successful.
  @override
  Future<bool?> printString(String text) async {
    final result = await methodChannel.invokeMethod<bool>('printString', text);
    return result;
  }

  /// Cuts the paper using the native platform's cutting functionality.
  ///
  /// Returns a [Future] that completes with a [bool] indicating whether the paper cutting was successful.
  @override
  Future<bool?> cutPaper() async {
    final result = await methodChannel.invokeMethod<bool>('cutPaper');
    return result;
  }
}
