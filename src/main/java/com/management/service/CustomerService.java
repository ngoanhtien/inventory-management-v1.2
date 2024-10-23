package com.management.service;

import com.management.model.Customer;
import com.management.parser.CustomerParser;
import com.management.utils.ErrorLogger;
import com.management.validator.CustomerValidator;

import java.util.ArrayList;
import java.util.List;

public class CustomerService extends BaseService<Customer> {

    private List<Customer> customers = new ArrayList<>();

    public CustomerService(ErrorLogger errorLogger) {
        super(new CustomerParser(), new CustomerValidator(), errorLogger);
    }

    @Override
    public List<Customer> getData(String inputFilePath) {
        customers = dataLoader.loadData(inputFilePath);
        return customers;
    }

    @Override
    public List<Customer> addDataList(String newDataFilePath) {

        return null;
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(customers, outputFilePath);
    }
}