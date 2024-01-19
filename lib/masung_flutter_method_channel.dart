import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'masung_flutter_platform_interface.dart';

/// An implementation of [MasungFlutterPlatform] that uses method channels.
class MethodChannelMasungFlutter extends MasungFlutterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('masung_flutter');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<bool?> printString(String text) async {
    final result = await methodChannel.invokeMethod<bool>('printString', text);
    return result;
  }

  @override
  Future<bool?> cutPaper() async {
    final result = await methodChannel.invokeMethod<bool>('cutPaper');
    return result;
  }
}
