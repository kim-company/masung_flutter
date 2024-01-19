import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'masung_flutter_method_channel.dart';

abstract class MasungFlutterPlatform extends PlatformInterface {
  /// Constructs a MasungFlutterPlatform.
  MasungFlutterPlatform() : super(token: _token);

  static final Object _token = Object();

  static MasungFlutterPlatform _instance = MethodChannelMasungFlutter();

  /// The default instance of [MasungFlutterPlatform] to use.
  ///
  /// Defaults to [MethodChannelMasungFlutter].
  static MasungFlutterPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [MasungFlutterPlatform] when
  /// they register themselves.
  static set instance(MasungFlutterPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<bool?> printString(String text) {
    throw UnimplementedError('printString() has not been implemented.');
  }

  Future<bool?> cutPaper() {
    throw UnimplementedError('cutPaper() has not been implemented.');
  }
}
