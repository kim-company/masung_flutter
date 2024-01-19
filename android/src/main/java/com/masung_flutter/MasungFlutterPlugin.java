package com.masung_flutter;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** MasungFlutterPlugin */
public class MasungFlutterPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private MasungPrinterCom printerCom;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "masung_flutter");
    channel.setMethodCallHandler(this);
    printerCom = new MasungPrinterCom();
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    switch (call.method) {
      case "getPlatformVersion":
        result.success("Android " + android.os.Build.VERSION.RELEASE);
        break;
      case "printString":
        String text = (String) call.arguments;
        if (text != null && printString(text)) {
          result.success(true);
        } else {
          result.error("UNAVAILABLE", "Printing failed.", false);
        }
        break;
      case "cutPaper":
        result.success(true);
        if (cutPaper()) {
          result.success(true);
        } else {
          result.error("UNAVAILABLE", "Cutting paper failed.", false);
        }
        break;
      default:
        result.notImplemented();
    }
  }

  private boolean cutPaper() {
    try{
      if(printerCom == null)
        printerCom = new MasungPrinterCom();
      printerCom.cutPaper();
      return true;
    }
    catch(Exception e){
      return false;
    }
  }

  private boolean printString(String text) {
    try {
      if(printerCom == null)
        printerCom = new MasungPrinterCom();
      printerCom.printText(text);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
