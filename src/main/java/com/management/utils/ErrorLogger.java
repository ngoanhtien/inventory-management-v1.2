package com.management.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorLogger {
    private BufferedWriter writer;

    public ErrorLogger(String filePath) {
        try {
            writer = new BufferedWriter(new FileWriter(filePath, false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logError(String error) {
        try {
            writer.write(error);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
