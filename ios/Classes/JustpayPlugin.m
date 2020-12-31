#import "JustpayPlugin.h"
#if __has_include(<justpayPlugin/justpayPlugin-Swift.h>)
#import <justpayPlugin/justpayPlugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "justpayPlugin-Swift.h"
#endif

@implementation JustpayPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftJustpayPlugin registerWithRegistrar:registrar];
}

//
// + (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar> *)registrar {
//   FlutterMethodChannel *channel =
//       [FlutterMethodChannel methodChannelWithName:@"plugins.flutter.io/local_auth"
//                                   binaryMessenger:[registrar messenger]];
//   JustpayPlugin *instance = [[JustpayPlugin alloc] init];
//   [registrar addMethodCallDelegate:instance channel:channel];
//   [registrar addApplicationDelegate:instance];
// }
//
// - (void)handleMethodCall:(FlutterMethodCall *)call result:(FlutterResult)result {
//   if ([@"getDeviceId" isEqualToString:call.method]) {
//     result([[device identifierForVendor] UUIDString])
//   } else if ([@"createIdentity" isEqualToString:call.method]) {
//     [self createIdentity:call.arguments withFlutterResult:result];
//   } else {
//     result(FlutterMethodNotImplemented);
//   }
// }
//
// - (void)createIdentity:(NSDictionary *)arguments withFlutterResult:(FlutterResult)result  {
//   LCTrustedSDK *lcTrustedSDK;
//    lcTrustedSDK = [[LCTrustedSDK alloc] init];
//    lcTrustedSDK.delegate = self ;
//    [lcTrustedSDK createIdentity: arguments[@"challengeKey"];
//    - (void)onIdentityFailed:(int)errorCode message:(NSString *)errorMessage {
//     }
//     - (void)onIdentitySuccess {
//     }
// }
@end
