package com.management.service;

import com.management.model.Customer;
import com.management.model.Order;
import com.management.model.Product;
import com.management.parser.OrderParser;
import com.management.utils.ErrorLogger;
import com.management.validator.OrderValidator;

import java.util.LinkedHashMap;
import java.util.List;

public class OrderService extends BaseService<String, Order> {
    private List<Product> productList;
    private List<Customer> customerList;

    public OrderService(ErrorLogger errorLogger, List<Customer> customerList, List<Product> productList) {
        super(new OrderParser(), new OrderValidator(customerList, productList), errorLogger);
    }

    @Override
    public void getData(String inputFilePath) {
        dataMap = dataLoader.loadDataToLinkedHashMap(inputFilePath);
    }

    @Override
    public void addDataList(String newDataFilePath) {

    }

    @Override
    public void editDataList(String editDataFilePath) {

    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {

    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}
