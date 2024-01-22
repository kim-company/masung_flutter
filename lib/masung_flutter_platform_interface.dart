import 'package:masung_flutter/masung_flutter_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

/// A platform interface for the Masung Flutter plugin.
///
/// This interface defines the methods that can be called on the Masung Flutter plugin
/// across different platforms (Android, iOS, etc.).
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

  /// Retrieves the platform version.
  ///
  /// Returns a [Future] that completes with the platform version as a [String].
  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  /// Prints the given [text] to the printer.
  ///
  /// Returns a [Future] that completes with a [bool] indicating whether the
  /// printing was successful or not.
  Future<bool?> printString(String text) {
    throw UnimplementedError('printString() has not been implemented.');
  }

  /// Cuts the paper.
  ///
  /// Returns a [Future] that completes with a [bool] indicating whether the
  /// paper cutting was successful or not.
  Future<bool?> cutPaper() {
    throw UnimplementedError('cutPaper() has not been implemented.');
  }
}
