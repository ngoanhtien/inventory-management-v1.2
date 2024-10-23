package com.management.service;

import com.management.model.Customer;
import com.management.model.Order;
import com.management.model.Product;
import com.management.parser.OrderParser;
import com.management.utils.ErrorLogger;
import com.management.validator.OrderValidator;

import java.util.ArrayList;
import java.util.List;

public class OrderService extends BaseService<Order> {
    private List<Product> productList;
    private List<Customer> customerList;
    private List<Order> orders = new ArrayList<>();

    public OrderService(ErrorLogger errorLogger, List<Customer> customerList, List<Product> productList) {
        super(new OrderParser(), new OrderValidator(customerList, productList), errorLogger);
    }

    @Override
    public List<Order> getData(String inputFilePath) {
        orders = dataLoader.loadData(inputFilePath);
        return orders;
    }

    @Override
    public List<Order> addDataList(String newDataFilePath) {

        return null;
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(orders, outputFilePath);
    }
}
