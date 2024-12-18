package com.management.dal;

import com.management.model.CSVConvertible;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class DataWriter<T> {
    public void writeData(Collection<T> dataCollection, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {

            for (T data : dataCollection) {
                if (data instanceof CSVConvertible) {
                    writer.write(((CSVConvertible) data).toCSV());
                    writer.newLine();
                } else {
                    writer.write(data.toString());
                    writer.newLine();
                }
            }
            System.out.println("Data successfully written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
