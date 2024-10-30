package com.management.service;

import com.management.model.Customer;
import com.management.model.Order;
import com.management.model.Product;
import com.management.parser.OrderParser;
import com.management.utils.ErrorLogger;
import com.management.validator.OrderValidator;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService extends BaseService<String, Order> {
    private Map<String, Product> productList;
    private Map<String, Customer> customerList;


    public OrderService(ErrorLogger errorLogger,
                        Map<String, Product> productList,
                        Map<String, Customer> customerList) {
        super(new OrderParser(), new OrderValidator(productList, customerList), errorLogger);
    }

    @Override
    public Map<String, Order> getData(String inputFilePath) {
        dataMap = dataLoader.loadDataToLinkedHashMap(inputFilePath);
        return dataMap;
    }

    @Override
    public void addDataList(String newDataFilePath) {
        //Load danh sach order moi len newDataMap
        //Them
        Map<String, Order> newDataMap = dataLoader.loadDataToLinkedHashMap(newDataFilePath);

        newDataMap.forEach((key, value) -> {
            if(dataMap.containsKey(key)) {
                errorLogger.logError("Duplicate Order: " + key);
            } else {
                dataMap.put(key, value);
            }
        });
    }

    @Override
    public void editDataList(String editDataFilePath) {
        Map<String, Order> editDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        dataMap.putAll(editDataMap);
    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {
        Map<String, Order> deleteDataMap = dataLoader.loadDataToLinkedHashMap(deleteDataFilePath);
        deleteDataMap.forEach((key,value) -> {
            if(dataMap.containsKey(key)) {
                dataMap.remove(key);
            } else {
                errorLogger.logError("Order is not exist to delete: " + key);
            }
        });
    }

    public Map<String, Order> hanldleFindOrdersByProductIds(Map<String, Order> orders, Set<String> productIds) {

        dataMap = orders.entrySet().stream()
                .filter(entry -> entry.getValue().getProductQuantities().keySet().stream()
                        .anyMatch(productIds::contains))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
        return  dataMap;
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}
