import 'package:flutter_test/flutter_test.dart';
import 'package:masung_flutter/masung_flutter.dart';
import 'package:masung_flutter/masung_flutter_platform_interface.dart';
import 'package:masung_flutter/masung_flutter_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockMasungFlutterPlatform
    with MockPlatformInterfaceMixin
    implements MasungFlutterPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final MasungFlutterPlatform initialPlatform = MasungFlutterPlatform.instance;

  test('$MethodChannelMasungFlutter is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelMasungFlutter>());
  });

  test('getPlatformVersion', () async {
    MasungFlutter masungFlutterPlugin = MasungFlutter();
    MockMasungFlutterPlatform fakePlatform = MockMasungFlutterPlatform();
    MasungFlutterPlatform.instance = fakePlatform;

    expect(await masungFlutterPlugin.getPlatformVersion(), '42');
  });
}
