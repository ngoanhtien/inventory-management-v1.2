package com.management.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorLogger {
    private BufferedWriter writer;

    public ErrorLogger(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath, true));
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

    public static void main(String[] args) {
        ErrorLogger errorLogger = null;
        try {
            errorLogger = new ErrorLogger("D:\\error.output.csv");
            errorLogger.logError("ABC");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (errorLogger != null) {
                errorLogger.close();  // Đảm bảo đóng writer sau khi ghi xong
            }
        }
    }
}
