import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:justpayPlugin/justpayPlugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('justpayPlugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await JustpayPlugin.platformVersion, '42');
  });

  test('getDeviceId', () async {
    print(await JustpayPlugin.getDeviceId());
//    expect(await JustpayPlugin.getDeviceId(), '12');
  });
  test('isIdentityExist', () async {
    print(await JustpayPlugin.isIdentityExist());
//    expect(await JustpayPlugin.getDeviceId(), '12');
  });

}
