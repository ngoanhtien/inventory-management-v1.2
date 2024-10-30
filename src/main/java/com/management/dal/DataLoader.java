package com.management.dal;

import com.management.model.Customer;
import com.management.model.Identifiable;
import com.management.model.Order;
import com.management.model.Product;
import com.management.parser.ModelParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

public class DataLoader<K, T extends Identifiable<K>> {
    private ModelParser<T> parser;
    private ErrorLogger errorLogger;
    private ModelValidator<T> validator;

    public DataLoader(ModelParser<T> parser, ModelValidator<T> validator, ErrorLogger errorLogger) {
        this.parser = parser;
        this.errorLogger = errorLogger;
        this.validator = validator;
    }

    public List<T> loadData(String filePath) {
        List<T> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                try {
                    T data = parser.parse(line);
                    validator.validate(data);
                    dataList.add(data);
                } catch (Exception e) {
                    errorLogger.logError(e.getMessage());
                }
            }
        } catch (IOException e) {
            errorLogger.logError("Error reading file: " + e.getMessage());
        }
        return dataList;
    }

    public Map<K, T> loadDataToLinkedHashMap(String filePath) {
        Map<K, T> dataLinkedHashMap = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                try {
                    T data = parser.parse(line);
                    validator.validate(data);
                    K key = getKey(data);
                    dataLinkedHashMap.put(key, data);
                } catch (Exception e) {
                    errorLogger.logError(e.getMessage());
                }
            }
        } catch (IOException e) {
            errorLogger.logError("Error reading file: " + e.getMessage());
        }
        return dataLinkedHashMap;
    }

    @SuppressWarnings("unchecked")
    private K getKey(T data) {
        if (data instanceof Product || data instanceof Order) {
            return data.getId();
        } else if (data instanceof Customer) {
            return (K) ((Customer) data).getPhoneNumber();
        }
        throw new IllegalArgumentException("Unable to determine key for data type: " + data.getClass().getSimpleName());
    }
}
