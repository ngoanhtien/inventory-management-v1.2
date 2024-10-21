package com.management.dal;

import com.management.model.CSVConvertible;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter<T extends CSVConvertible> {
    public void writeData(List<T> dataList, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (T data : dataList) {
                bw.write(data.toCSV());
                bw.newLine();
            }
            System.out.println("Data successfully written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
