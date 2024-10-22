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
    public void getData(String inputFilePath) {
        customers = dataLoader.loadData(inputFilePath);
    }

    @Override
    public void addDataList(String newDataFilePath) {

    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(customers, outputFilePath);
    }
}