package com.masung_flutter.msprintsdk;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

public class PrinterService {
    private static final String ACTION_USB_PERMISSION = "com.usb.sample.USB_PERMISSION";
    private UsbDriver usbDriver;
    private UsbDevice device;
    private Context context;

    public PrinterService(Context context) {
        this.context = context;
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        this.usbDriver = new UsbDriver(usbManager, context);
        this.usbDriver.setPermissionIntent(PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0));
        initializePrinter();
    }

    private void initializePrinter() {
        if (!usbDriver.isConnected()) {
            for (UsbDevice device : ((UsbManager) context.getSystemService(Context.USB_SERVICE)).getDeviceList().values()) {
                if ((device.getProductId() == 8211 && device.getVendorId() == 1305) || (device.getProductId() == 8213 && device.getVendorId() == 1305)) {
                    if (this.usbDriver.usbAttached(device)) {
                        if (this.usbDriver.openUsbDevice(device)) {
                            this.device = device;
                        }
                    }
                }
            }
        }
    }

    public void sendBytesToPrinter(byte[] data) {
        if (device != null) {
            usbDriver.write(data, device);
        }
    }
}