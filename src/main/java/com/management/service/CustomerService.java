package com.management.service;

import com.management.model.Customer;
import com.management.parser.CustomerParser;
import com.management.utils.ErrorLogger;
import com.management.validator.CustomerValidator;

import java.util.LinkedHashMap;
import java.util.List;

public class CustomerService extends BaseService<String, Customer> {

    public CustomerService(ErrorLogger errorLogger) {
        super(new CustomerParser(), new CustomerValidator(), errorLogger);
    }

    @Override
    public void getData(String inputFilePath) {
        List<Customer> customerList = dataLoader.loadData(inputFilePath);

        customerList.forEach(customer -> {
            String phoneNumber = customer.getPhoneNumber();
            if(dataMap.containsKey(phoneNumber)) {
                errorLogger.logError("Duplicate customer phoneNumber " + phoneNumber + " in " + customer);
            } else {
                dataMap.put(phoneNumber, customer);
            }
        });
    }

    @Override
    public void addDataList(String newDataFilePath) {
        LinkedHashMap<String, Customer> newDataMap = dataLoader.loadDataToLinkedHashMap(newDataFilePath);

        //Key la phoneNumber, value la Customer
        //Key cua dataMap van la id
        newDataMap.forEach((key, value) -> {
            String phoneNumber = value.getPhoneNumber();
            if(dataMap.containsKey(phoneNumber)) {
                errorLogger.logError("Duplicate customer phone number " + key + " in " + value);
            } else {
                dataMap.put(key, value);
            }
        });
    }

    @Override
    public void editDataList(String editDataFilePath) {
        LinkedHashMap<String, Customer> editDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        dataMap.putAll(editDataMap);
    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {
        LinkedHashMap<String, Customer> deleteDataMap = dataLoader.loadDataToLinkedHashMap(deleteDataFilePath);

        deleteDataMap.forEach((key, value) -> {
            dataMap.remove(key);
        });
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}