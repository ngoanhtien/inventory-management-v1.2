package com.management.service;

import com.management.model.Order;
import com.management.model.Product;
import com.management.parser.ProductParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ProductValidator;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService extends BaseService<String, Product> {

    public ProductService(ErrorLogger errorLogger) {
        super(new ProductParser(), new ProductValidator(), errorLogger);
    }

    @Override
    public Map<String, Product> getData(String inputFilePath) {
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
        Map<String, Product> newDataMap = dataLoader.loadDataToLinkedHashMap(newDataFilePath);

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
        Map<String, Product> editDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        dataMap.putAll(editDataMap);
    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {
        Map<String, Product> deleteDataMap = dataLoader.loadDataToLinkedHashMap(deleteDataFilePath);
        deleteDataMap.forEach((key,value) -> {
            if(dataMap.containsKey(key)) {
                dataMap.remove(key);
            } else {
                errorLogger.logError("Product is not exist to delete: " + key);
            }
        });
    }

    public void findTop3ProductsByOrderQuantity(Map<String, Order> orders, Map<String, Product> products) {
        dataMap = orders.values().stream()
                .flatMap(order -> order.getProductQuantities().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .filter(entry -> products.containsKey(entry.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> products.get(entry.getKey()),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public Set<String> getProductIds(String folderPath) {
        List<Product> products = dataLoader.loadData(folderPath + File.separator + BaseService.PRODUCT_SEARCH_FILEPATH);

        return products.stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
    }

    public List<Product> getProductList(){
        return dataLoader.loadData(BaseService.PRODUCT_INPUT_FILEPATH);
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}
