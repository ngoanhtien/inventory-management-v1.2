package com.management.service;

import com.management.model.Customer;
import com.management.parser.CustomerParser;
import com.management.utils.ErrorLogger;
import com.management.validator.CustomerValidator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerService extends BaseService<String, Customer> {

    public CustomerService(ErrorLogger errorLogger) {
        super(new CustomerParser(), new CustomerValidator(), errorLogger);
    }

    @Override
    public Map<String, Customer> getData(String inputFilePath) {
        List<Customer> customerList = dataLoader.loadData(inputFilePath);

        customerList.forEach(customer -> {
            String phoneNumber = customer.getPhoneNumber();
            String id = customer.getId();
            String email = customer.getEmail();

            boolean isDuplicate = dataMap.values().stream().anyMatch(existingCustomer ->
                    existingCustomer.getPhoneNumber().equals(phoneNumber) ||
                            existingCustomer.getId().equals(id) ||
                            existingCustomer.getEmail().equals(email)
            );

            if (isDuplicate) {
                errorLogger.logError("Duplicate customer detected: phoneNumber=" + phoneNumber +
                        ", id=" + id + ", email=" + email);
            } else {
                dataMap.put(phoneNumber, customer);
            }
        });
        return dataMap;
    }

    @Override
    public void addDataList(String newDataFilePath) {
//        1.1. Nếu trùng phone thì cập nhật id, email, name
//            2.1. Nếu trùng id thì cập nhật email, name (Trùng phải so sánh với tất cả)
//                3.1. Nếu trùng email thì cập nhật mỗi name
//                3.2. Nếu không trùng email thì cập nhật cả email, name
//            2.2. Nếu không trùng id thì cập nhật id, email, name
//                3.3. Nếu trùng email thì cập nhật name
//                3.4. Nếu không trùng email thì cập nhật email, name
//        1.2. Nếu không trùng phone thì thêm mới
//            2.3. if trùng id thì ghi lỗi trùng id
//            2.4. if trùng email thì ghi lỗi trùng email
//            2.5. else thêm mới
        Map<String, Customer> newDataMap = dataLoader.loadDataToLinkedHashMap(newDataFilePath);

        newDataMap.forEach((newPhoneNumber, newCustomer) -> {
            String newId = newCustomer.getId();
            String newEmail = newCustomer.getEmail();
            String newName = newCustomer.getName();

            if (dataMap.containsKey(newPhoneNumber)) {
                Customer existingCustomer = dataMap.get(newPhoneNumber);

                boolean idDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getId().equals(newId));
                if (idDuplicate) {
                    boolean emailDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getEmail().equals(newEmail));
                    if (emailDuplicate) {
                        existingCustomer.setName(newName);
                    } else {
                        existingCustomer.setEmail(newEmail);
                        existingCustomer.setName(newName);
                    }
                } else {
                    existingCustomer.setId(newId);

                    boolean emailDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getEmail().equals(newEmail));
                    if (emailDuplicate) {
                        existingCustomer.setName(newName);
                    } else {
                        existingCustomer.setEmail(newEmail);
                        existingCustomer.setName(newName);
                    }
                }
                dataMap.put(newPhoneNumber, existingCustomer);
            } else {
                boolean idDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getId().equals(newId));
                boolean emailDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getEmail().equals(newEmail));

                if (idDuplicate) {
                    errorLogger.logError("Duplicate ID " + newId + " for customer " + newCustomer);
                }
                else if (emailDuplicate) {
                    errorLogger.logError("Duplicate email " + newEmail + " for customer " + newCustomer);
                }
                else {
                    dataMap.put(newPhoneNumber, newCustomer);
                }
            }
        });
    }

    @Override
    public void editDataList(String editDataFilePath) {
        Map<String, Customer> editDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        editDataMap.forEach((key, value) -> {
            if(dataMap.containsKey(key)) {
                dataMap.put(key, value);
            } else {
                errorLogger.logError("Not exist customer has phone number: " + value.getPhoneNumber());
            }
        });
    }

    @Override
    public void deleteDataList(String deleteDataFilePath) {
        Map<String, Customer> deleteDataMap = dataLoader.loadDataToLinkedHashMap(deleteDataFilePath);

        deleteDataMap.forEach((key, value) -> {
            if(dataMap.containsKey(key)) {
                dataMap.remove(key);
            } else {
                errorLogger.logError("Customer is not exist to delete " + key);
            }
        });
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(dataMap.values(), outputFilePath);
    }
}