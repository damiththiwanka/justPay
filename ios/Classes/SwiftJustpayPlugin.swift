import Flutter
import UIKit
import LCTrustedSDK



public class SwiftJustpayPlugin: NSObject, FlutterPlugin, LCIdentityDelegate {
  var lcTrustedSDK: LCTrustedSDK?
  var flutterResult: FlutterResult?
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "justpayPlugin", binaryMessenger: registrar.messenger())
    let instance = SwiftJustpayPlugin()
    instance.lcTrustedSDK = LCTrustedSDK()
    instance.setDelegate()
    registrar.addMethodCallDelegate(instance, channel: channel)
    
  }
    func setDelegate () {
        lcTrustedSDK!.delegate = self
    }

//  public func sendResponse(result: AnyObject){
//          if let _res = self.flutterResult{
//              _res(result) //methenata hit wenne naha
//          } else {
//
//    }
//      }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    self.flutterResult = result
    switch (call.method) {
            case "getDeviceId":
                    result(UIDevice.current.identifierForVendor!.uuidString as String)
                break
            case "isIdentityExist":
                    result(UIDevice.current.identifierForVendor!.uuidString as String)
                break
            case "createIdentity":
                    let challengeKey = (call.arguments as? [String : String])!["challengeKey"] as! String
                    self.createIdentity(arguments:challengeKey, result: result)
                break
            case "signMessage":
                    let message = (call.arguments as? [String : String])!["message"] as! String
                    self.signMessage(arguments:message, result: result)
                break
            default:
                result(FlutterMethodNotImplemented)
            }
  }

    func createIdentity(arguments:String, result: @escaping FlutterResult) -> Void {
        // let someInfo1 : String = arguments["challengeKey"] as! String
        lcTrustedSDK!.createIdentity(arguments)
    }
    public func onIdentitySuccess() {
        print("fire onIdentitySuccess")
        flutterResult!("Success")
    }
    public func onIdentityFailed(_ errorCode: Int32, message errorMessage: String!){
        print("error onIdentityFailed")
        flutterResult!(FlutterError(code: String(errorCode), message: errorMessage, details: nil))

    }
    func signMessage(arguments:String, result: @escaping FlutterResult) -> Void {
    // let someInfo1 : String = arguments["message"] as! String
        lcTrustedSDK?.signMessage(arguments)
//     result(arguments)
    }
    public func onMessageSignSuccess(_ signedMessage: String!, status: String! ) {
        flutterResult!(signedMessage)
    }
    public func onMessageSignFailed(_ errorCode: Int32, message errorMessage: String!) {
        flutterResult!(FlutterError(code: String(errorCode), message: errorMessage, details: nil))
    }
}
