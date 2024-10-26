package com.management.dal;

import com.management.model.Customer;
import com.management.model.Identifiable;
import com.management.model.Order;
import com.management.model.Product;
import com.management.parser.ModelParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

public class DataLoader<K, T extends Identifiable<K>> {
    private ModelParser<T> parser;
    private ErrorLogger errorLogger;
    private ModelValidator<T> validator;

//    private Map<String, Set<String>> existingFields;

    public DataLoader(ModelParser<T> parser, ModelValidator<T> validator, ErrorLogger errorLogger) {
        this.parser = parser;
        this.errorLogger = errorLogger;
        this.validator = validator;
//        this.existingFields = new HashMap<>();

        // Thiết lập existingFields cho validator
//        this.validator.setExistingFields(this.existingFields);
    }

    public List<T> loadData(String filePath) {
        List<T> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
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

    public LinkedHashMap<K, T> loadDataToLinkedHashMap(String filePath) {
        LinkedHashMap<K, T> dataLinkedHashMap = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
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

    // Phương thức getKey
    @SuppressWarnings("unchecked")
    private K getKey(T data) {
        if (data instanceof Product || data instanceof Order) {
            return data.getId(); // Trả về id cho Product hoặc Order
        } else if (data instanceof Customer) {
            return (K) ((Customer) data).getPhoneNumber(); // Trả về phone number cho Customer
        }
        throw new IllegalArgumentException("Unable to determine key for data type: " + data.getClass().getSimpleName());
    }
}
