package com.management.service;

import com.management.model.Product;
import com.management.parser.ProductParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ProductValidator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductService extends BaseService<String, Product> {

//    private LinkedHashMap<String, Product> products;

    public ProductService(ErrorLogger errorLogger) {
        super(new ProductParser(), new ProductValidator(), errorLogger);
    }

    @Override
    public void getData(String inputFilePath) {
        List<Product> productList = dataLoader.loadData(inputFilePath);
        dataMap = new LinkedHashMap<>();

        productList.forEach(product -> {
            String productId = product.getId();
            if(dataMap.containsKey(productId)) {
                errorLogger.logError("Duplicate product: " + product);
            } else {
                dataMap.put(productId, product);
            }
        });
    }

    @Override
    public void addDataList(String newDataFilePath) {
        LinkedHashMap<String, Product> newDataMap = dataLoader.loadDataToLinkedHashMap(newDataFilePath);

        newDataMap.forEach((key,value) -> {
            if(dataMap.containsKey(key)) {
                errorLogger.logError("Duplicate product: " + key);
            } else {
                dataMap.put(key, value);
            }
        });
    }

    @Override
    public void editDataList(String editDataFilePath) {
        LinkedHashMap<String, Product> newDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        dataMap.putAll(newDataMap);
    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {
        LinkedHashMap<String, Product> deleteDataMap = dataLoader.loadDataToLinkedHashMap(deleteDataFilePath);
        deleteDataMap.forEach((key,value) -> {
            dataMap.remove(key);
        });
    }


    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}
