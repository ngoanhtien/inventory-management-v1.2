package com.management.service;

import com.management.model.Customer;
import com.management.parser.CustomerParser;
import com.management.utils.ErrorLogger;
import com.management.validator.CustomerValidator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CustomerService extends BaseService<String, Customer> {

    public CustomerService(ErrorLogger errorLogger) {
        super(new CustomerParser(), new CustomerValidator(), errorLogger);
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