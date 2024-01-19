package com.masung_flutter;

import com.masung_flutter.model.SerialPortFinder;
import com.masung_flutter.msprintsdk.JNACom;

public class MasungPrinterCom {
    private SerialPortFinder mSerialPortFinder;
    private void initializePrinter() {
        mSerialPortFinder = new SerialPortFinder();
        connectToFirstAvailablePrinter();
    }

    private void connectToFirstAvailablePrinter() {
        String[] availablePorts = mSerialPortFinder.getAllDevicesPath();
        if (availablePorts.length > 0) {
            // Assuming the first port is the printer port
            String printerPort = availablePorts[0];
            JNACom.INSTANCE.SetDevname(1, printerPort, 9600); // Example baud rate
            JNACom.INSTANCE.SetInit();
        }
    }

}
