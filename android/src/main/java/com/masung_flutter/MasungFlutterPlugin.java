package com.masung_flutter;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * MasungFlutterPlugin is a Flutter plugin that provides an interface for Flutter applications to interact with a Masung printer.
 * It implements the FlutterPlugin and MethodCallHandler interfaces.
 */
public class MasungFlutterPlugin implements FlutterPlugin, MethodCallHandler {

  // The MethodChannel that will the communication between Flutter and native Android
  // This local reference serves to register the plugin with the Flutter Engine and unregister it
  // when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private MasungPrinterCom printerCom;

  /**
   * This method is called when the plugin is attached to the Flutter engine.
   * It initializes the MethodChannel and the MasungPrinterCom instance.
   * @param flutterPluginBinding the binding with the Flutter plugin
   */
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "masung_flutter");
    channel.setMethodCallHandler(this);
    printerCom = new MasungPrinterCom();
  }

  /**
   * This method is called when a method call is made from Flutter.
   * It handles the method calls for getting the platform version, printing a string, and cutting paper.
   * @param call the method call
   * @param result the result to be returned to Flutter
   */
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

  /**
   * This method is used to cut the paper.
   * It creates a new instance of MasungPrinterCom if it doesn't exist and calls the cutPaper method.
   * @return true if the paper was cut successfully, false otherwise
   */
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

  /**
   * This method is used to print a string.
   * It creates a new instance of MasungPrinterCom if it doesn't exist and calls the printText method.
   * @param text the text to be printed
   * @return true if the text was printed successfully, false otherwise
   */
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

  /**
   * This method is called when the plugin is detached from the Flutter engine.
   * It unregisters the MethodCallHandler from the MethodChannel.
   * @param binding the binding with the Flutter plugin
   */
  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}