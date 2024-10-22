package com.management.dal;

import com.management.parser.ModelParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

public class DataLoader<T> {
    private ModelParser<T> parser;
    private ErrorLogger errorLogger;
    private ModelValidator<T> validator = null;

    public DataLoader(ModelParser<T> parser, ModelValidator<T> validator, ErrorLogger errorLogger) {
        this.parser = parser;
        this.errorLogger = errorLogger;
        this.validator = validator;
    }

    public List<T> loadData(String filePath) {
        List<T> dataList = new ArrayList<>();
        List<Set<String>> existFields = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    T data = parser.parse(line);
                    validator.validate(data, existFields);
                    dataList.add(data);
                } catch (Exception e) {
                    errorLogger.logError(e.getMessage());
                    continue;
                }
            }
        } catch (IOException e) {
            errorLogger.logError("Error reading file: " + e.getMessage());
        }
        return dataList;
    }


}
