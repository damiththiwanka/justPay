import 'dart:async';

import 'package:flutter/services.dart';

class JustpayPlugin {
  static const MethodChannel _channel =
      const MethodChannel('justpayPlugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> getDeviceId() async {
    final String version = await _channel.invokeMethod('getDeviceId');
    return version;
  }
  static Future<String> isIdentityExist() async {
    final String version = await _channel.invokeMethod('isIdentityExist');
    return version;
  }
  static Future<String> createIdentity(String challengeKey) async {
    final String version = await _channel.invokeMethod('createIdentity', {
      "challengeKey" : challengeKey
    });
    return version;
  }
  static Future<String> signMessage(String message) async {
    final String version = await _channel.invokeMethod('signMessage', {
      "message" : message
    });
    return version;
  }
}
