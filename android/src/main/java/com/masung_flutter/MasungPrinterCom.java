package com.masung_flutter;

import android.content.Context;
import com.masung_flutter.msprintsdk.PrintCmd;
import com.masung_flutter.msprintsdk.PrinterService;



/**
 * MasungPrinterCom is a class that provides an interface for interacting with a Masung printer.
 * It provides methods for initializing the printer, printing text and images, cutting paper, and getting the printer status.
 */
public class MasungPrinterCom {

    PrinterService printerService;

    /**
     * Constructor for the MasungPrinterCom class.
     * It calls the initializePrinter method to set up the printer.
     */
    public MasungPrinterCom(Context context) {
        printerService = new PrinterService(context);
    }

    /**
     * This method prints a text string.
     * @param text the text to be printed
     */
    public void printText(String text) {
        String command = PrintCmd.JNAByteToString(PrintCmd.PrintString(text, 0));
        printerService.sendBytesToPrinter(command.getBytes());
    }

    /**
     * This method prints an image.
     * @param imagePath the path to the image file
     */
    public void printImage(String imagePath) {
        byte[] imageData = PrintCmd.PrintDiskImagefile(imagePath);
        if (imageData != null) {
            String command = PrintCmd.JNAByteToString(imageData);
            printerService.sendBytesToPrinter(command.getBytes());
        }
    }

    /**
     * This method cuts the paper.
     */
    public void cutPaper() {
        String command = PrintCmd.JNAByteToString(PrintCmd.PrintCutpaper(1));
        printerService.sendBytesToPrinter(command.getBytes());
    }

}