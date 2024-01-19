// This is a basic Flutter integration test.
//
// Since integration tests run in a full Flutter application, they can interact
// with the host side of a plugin implementation, unlike Dart unit tests.
//
// For more information about Flutter integration tests, please see
// https://docs.flutter.dev/cookbook/testing/integration/introduction

import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';

import 'package:masung_flutter/masung_flutter.dart';

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  testWidgets('getPlatformVersion test', (WidgetTester tester) async {
    final MasungFlutter plugin = MasungFlutter();
    final String? version = await plugin.getPlatformVersion();
    // The version string depends on the host platform running the test, so
    // just assert that some non-empty string is returned.
    expect(version?.isNotEmpty, true);
  });

  testWidgets('print text test', (WidgetTester tester) async {
    final MasungFlutter plugin = MasungFlutter();
    final bool? result = await plugin.printString("Hello World");
    // The version string depends on the host platform running the test, so
    // just assert that some non-empty string is returned.
    expect(result, true);
  });

  testWidgets('Cut paper test', (WidgetTester tester) async {
    final MasungFlutter plugin = MasungFlutter();
    var result = await plugin.printString("Hello World");
    // The version string depends on the host platform running the test, so
    // just assert that some non-empty string is returned.
    expect(result, true);
  });
}
