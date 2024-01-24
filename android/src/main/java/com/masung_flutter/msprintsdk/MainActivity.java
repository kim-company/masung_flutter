//package com.masung_flutter.msprintsdk;
//
//import android.app.Activity;
//import android.app.PendingIntent;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.hardware.usb.UsbDevice;
//import android.hardware.usb.UsbManager;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class MainActivity extends Activity {
//    private static final String ACTION_USB_PERMISSION = "com.usb.sample.USB_PERMISSION";
//    private Button mBtnPrint;
//
//    /* access modifiers changed from: private */
//    public UsbDriver mUsbDriver;
//    BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
//        public void onReceive(android.content.Context r8, Intent r9) {
//            throw new UnsupportedOperationException("Method not decompiled: com.example.updatefirware.MainActivity.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
//        }
//    };
//    public UsbDevice m_Device;
//    public int m_IntOper = 0;
//    String m_StrPrint = "print test\n";
//    private String m_strPrompt = "";
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        init();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }
//
//    public void init() {
//        this.mBtnPrint.setOnClickListener(new PrintClickListener());
//        this.mUsbDriver = new UsbDriver((UsbManager) getSystemService(Context.USB_SERVICE), this);
//        this.mUsbDriver.setPermissionIntent(PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ACTION_USB_PERMISSION);
//        filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
//        filter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
//        registerReceiver(this.mUsbReceiver, filter);
//    }
//
//    public boolean PrintConnStatus() {
//        try {
//            if (this.mUsbDriver.isConnected()) {
//                return true;
//            }
//            for (UsbDevice device : ((UsbManager) getSystemService(Context.USB_SERVICE)).getDeviceList().values()) {
//                if ((device.getProductId() == 8211 && device.getVendorId() == 1305) || (device.getProductId() == 8213 && device.getVendorId() == 1305)) {
//                    promptUpdate("Connect printer...");
//                    boolean blnRtn = this.mUsbDriver.usbAttached(device);
//                    if (!blnRtn) {
//                        return blnRtn;
//                    }
//                    boolean blnRtn2 = this.mUsbDriver.openUsbDevice(device);
//                    this.m_Device = device;
//                    return blnRtn2;
//                }
//            }
//            return false;
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(), 0).show();
//            return false;
//        }
//    }
//
//    class PrintClickListener implements View.OnClickListener {
//        PrintClickListener() {
//        }
//
//        public void onClick(View view) {
//            try {
//                if (!MainActivity.this.PrintConnStatus()) {
//                    MainActivity.this.m_IntOper = 1;
//                    MainActivity.this.promptUpdate("Please wait...");
//                } else if (MainActivity.this.mUsbDriver.write(MainActivity.this.m_StrPrint.getBytes()) < 0) {
//                    MainActivity.this.mUsbDriver.closeUsbDevice(MainActivity.this.m_Device);
//                }
//            } catch (Exception e) {
//                MainActivity.this.promptUpdate(e.getMessage());
//            }
//        }
//    }
//
//    public void promptUpdate(String strInfo) {
//        this.m_strPrompt = String.valueOf(this.m_strPrompt) + "\n" + new SimpleDateFormat(" HH:mm:ss").format(new Date(System.currentTimeMillis())) + ":" + strInfo;
//        this.mEdt_Prompt.setText(this.m_strPrompt);
//    }
//}
