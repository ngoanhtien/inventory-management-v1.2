package com.management.service;

import com.management.model.Order;
import com.management.model.Product;
import com.management.parser.ProductParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ProductValidator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService extends BaseService<String, Product> {

    public ProductService(ErrorLogger errorLogger) {
        super(new ProductParser(), new ProductValidator(), errorLogger);
    }

    @Override
    public LinkedHashMap<String, Product> getData(String inputFilePath) {
        List<Product> productList = dataLoader.loadData(inputFilePath);
        dataMap = new LinkedHashMap<>();

        productList.forEach(product -> {
            String productId = product.getId();
            if(dataMap.containsKey(productId)) {
                errorLogger.logError("Duplicate product id " + productId + " in " + product);
            } else {
                dataMap.put(productId, product);
            }
        });
        return dataMap;
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
        LinkedHashMap<String, Product> editDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        dataMap.putAll(editDataMap);
    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {
        LinkedHashMap<String, Product> deleteDataMap = dataLoader.loadDataToLinkedHashMap(deleteDataFilePath);
        deleteDataMap.forEach((key,value) -> {
            dataMap.remove(key);
        });
    }

    public void findTop3ProductsByOrderQuantity(LinkedHashMap<String, Order> orders, LinkedHashMap<String, Product> products) {
        // Tính tổng số lượng order cho mỗi productId
        dataMap = orders.values().stream() // Duyệt qua các giá trị (Order) trong LinkedHashMap
                .flatMap(order -> order.getProductQuantities().entrySet().stream()) // Duyệt qua tất cả các product của mỗi order
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey, // Nhóm theo productId
                        Collectors.summingInt(Map.Entry::getValue) // Tính tổng quantity cho mỗi productId
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Sắp xếp giảm dần theo số lượng order
                .limit(3) // Lấy top 3
                .filter(entry -> products.containsKey(entry.getKey())) // Kiểm tra productId có tồn tại trong products
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Key là productId
                        entry -> products.get(entry.getKey()), // Value là Product tương ứng
                        (oldValue, newValue) -> oldValue, // Giải quyết trường hợp trùng key
                        LinkedHashMap::new // Sử dụng LinkedHashMap để giữ thứ tự
                ));
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}
