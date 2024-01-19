package com.masung_flutter;

import com.masung_flutter.model.SerialPortFinder;
import com.masung_flutter.msprintsdk.JNACom;
import com.masung_flutter.msprintsdk.PrintCmd;

import java.util.Objects;

public class MasungPrinterCom {

    public MasungPrinterCom() {
       initializePrinter();
    }

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
    public void configureSerialPort(String portName, int baudRate) {
        JNACom.INSTANCE.SetDevname(1, portName, baudRate);
        JNACom.INSTANCE.SetInit();
    }

    public void printText(String text) {
        String command = PrintCmd.JNAByteToString(PrintCmd.PrintString(text, 0));
        JNACom.INSTANCE.PrintTransmitJNA(command, command.length());
    }

    public void printImage(String imagePath) {
        byte[] imageData = PrintCmd.PrintDiskImagefile(imagePath);
        if (imageData != null) {
            String command = PrintCmd.JNAByteToString(imageData);
            JNACom.INSTANCE.PrintTransmitJNA(command, command.length());
        }
    }

    public void cutPaper() {
        String command = PrintCmd.JNAByteToString(PrintCmd.PrintCutpaper(1));
        JNACom.INSTANCE.PrintTransmitJNA(command, command.length());
    }

    public int getPrinterStatus() {
        String statusCommand = PrintCmd.JNAByteToString(Objects.requireNonNull(PrintCmd.GetStatus1()));
        byte[] status = JNACom.INSTANCE.GetTransmitJNA(statusCommand, statusCommand.length()).getBytes();
        return PrintCmd.CheckStatus1(status[0]);
    }

    public void printMultipleCopies(String text, int copies) {
        for (int i = 0; i < copies; i++) {
            printText(text);
            try {
                Thread.sleep(1000); // Delay of 1 second between copies
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPrintQuality(int quality) {
        String qualityCommand = "SET_QUALITY " + quality;
        JNACom.INSTANCE.PrintTransmitJNA(qualityCommand, qualityCommand.length());
    }

}
