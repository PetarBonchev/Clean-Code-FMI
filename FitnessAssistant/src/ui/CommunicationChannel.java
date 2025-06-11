package org.example.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CommunicationChannel {
    private final Scanner inputScanner;
    private final PrintStream outputStream;

    public CommunicationChannel(InputStream input, PrintStream output) {
        this.inputScanner = new Scanner(input);
        this.outputStream = output;
    }

    /**
     * Returns String with read line from Scanner
     */
    public String readLine() {
        return inputScanner.nextLine();
    }

    /**
     * Writes line on PrintStream
     */
    public void writeLine(String line) {
        outputStream.println(line);
    }
}
