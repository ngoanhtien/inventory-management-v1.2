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
    private LinkedHashMap<String, Product> productList;
    private LinkedHashMap<String, Customer> customerList;


    public OrderService(ErrorLogger errorLogger,
                        LinkedHashMap<String, Product> productList,
                        LinkedHashMap<String, Customer> customerList) {
        super(new OrderParser(), new OrderValidator(productList, customerList), errorLogger);
    }

    @Override
    public LinkedHashMap<String, Order> getData(String inputFilePath) {
        dataMap = dataLoader.loadDataToLinkedHashMap(inputFilePath);
        return dataMap;
    }

    @Override
    public void addDataList(String newDataFilePath) {
        //Load danh sach order moi len newDataMap
        //Them
        LinkedHashMap<String, Order> newDataMap = dataLoader.loadDataToLinkedHashMap(newDataFilePath);

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
        LinkedHashMap<String, Order> editDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        dataMap.putAll(editDataMap);
    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {
        LinkedHashMap<String, Order> deleteDataMap = dataLoader.loadDataToLinkedHashMap(deleteDataFilePath);
        deleteDataMap.forEach((key,value) -> {
            dataMap.remove(key);
        });
    }

    public LinkedHashMap<String, Order> hanldleFindOrdersByProductIds(LinkedHashMap<String, Order> orders, Set<String> productIds) {
        return orders.entrySet().stream() // Duyệt qua các entry (cặp key-value) trong LinkedHashMap
                .filter(entry -> entry.getValue().getProductQuantities().keySet().stream() // Lấy danh sách productId trong Order
                        .anyMatch(productIds::contains)) // Kiểm tra nếu bất kỳ productId nào thuộc productIds
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Key là id của Order
                        Map.Entry::getValue, // Value là đối tượng Order
                        (oldValue, newValue) -> oldValue, // Giải quyết trường hợp trùng key
                        LinkedHashMap::new // Sử dụng LinkedHashMap để giữ thứ tự ban đầu
                ));
    }

    public LinkedHashMap<String, Order> hanldeSearchOrderByProductId(LinkedHashMap<String, Product> productList) {

        return null;
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}
