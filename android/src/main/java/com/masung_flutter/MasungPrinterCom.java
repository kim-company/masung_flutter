package com.masung_flutter;

import com.masung_flutter.model.SerialPortFinder;
import com.masung_flutter.msprintsdk.JNACom;
import com.masung_flutter.msprintsdk.PrintCmd;

import java.util.Objects;

/**
 * MasungPrinterCom is a class that provides an interface for interacting with a Masung printer.
 * It provides methods for initializing the printer, printing text and images, cutting paper, and getting the printer status.
 */
public class MasungPrinterCom {

    /**
     * Constructor for the MasungPrinterCom class.
     * It calls the initializePrinter method to set up the printer.
     */
    public MasungPrinterCom() {
       initializePrinter();
    }

    private SerialPortFinder mSerialPortFinder;

    /**
     * This method initializes the printer.
     * It creates a new instance of SerialPortFinder and calls the connectToFirstAvailablePrinter method.
     */
    private void initializePrinter() {
        mSerialPortFinder = new SerialPortFinder();
        connectToFirstAvailablePrinter();
    }

    /**
     * This method connects to the first available printer.
     * It gets the list of all available ports and connects to the first one.
     */
    private void connectToFirstAvailablePrinter() {
        String[] availablePorts = mSerialPortFinder.getAllDevicesPath();
        if (availablePorts.length > 0) {
            // Assuming the first port is the printer port
            String printerPort = availablePorts[0];
            JNACom.INSTANCE.SetDevname(1, printerPort, 9600); // Example baud rate
            JNACom.INSTANCE.SetInit();
        }
    }

    /**
     * This method configures the serial port for the printer.
     * @param portName the name of the port
     * @param baudRate the baud rate for the port
     */
    public void configureSerialPort(String portName, int baudRate) {
        JNACom.INSTANCE.SetDevname(1, portName, baudRate);
        JNACom.INSTANCE.SetInit();
    }

    /**
     * This method prints a text string.
     * @param text the text to be printed
     */
    public void printText(String text) {
        String command = PrintCmd.JNAByteToString(PrintCmd.PrintString(text, 0));
        JNACom.INSTANCE.PrintTransmitJNA(command, command.length());
    }

    /**
     * This method prints an image.
     * @param imagePath the path to the image file
     */
    public void printImage(String imagePath) {
        byte[] imageData = PrintCmd.PrintDiskImagefile(imagePath);
        if (imageData != null) {
            String command = PrintCmd.JNAByteToString(imageData);
            JNACom.INSTANCE.PrintTransmitJNA(command, command.length());
        }
    }

    /**
     * This method cuts the paper.
     */
    public void cutPaper() {
        String command = PrintCmd.JNAByteToString(PrintCmd.PrintCutpaper(1));
        JNACom.INSTANCE.PrintTransmitJNA(command, command.length());
    }

    /**
     * This method gets the status of the printer.
     * @return the status of the printer
     */
    public int getPrinterStatus() {
        String statusCommand = PrintCmd.JNAByteToString(Objects.requireNonNull(PrintCmd.GetStatus1()));
        byte[] status = JNACom.INSTANCE.GetTransmitJNA(statusCommand, statusCommand.length()).getBytes();
        return PrintCmd.CheckStatus1(status[0]);
    }

    /**
     * This method prints multiple copies of a text string.
     * @param text the text to be printed
     * @param copies the number of copies to be printed
     */
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

    /**
     * This method sets the print quality.
     * @param quality the quality to be set
     */
    public void setPrintQuality(int quality) {
        String qualityCommand = "SET_QUALITY " + quality;
        JNACom.INSTANCE.PrintTransmitJNA(qualityCommand, qualityCommand.length());
    }

}