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
    public LinkedHashMap<String, Customer> getData(String inputFilePath) {
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
        LinkedHashMap<String, Customer> newDataMap = dataLoader.loadDataToLinkedHashMap(newDataFilePath);

        newDataMap.forEach((newPhoneNumber, newCustomer) -> {
            String newId = newCustomer.getId();
            String newEmail = newCustomer.getEmail();
            String newName = newCustomer.getName();

            // 1.1 Check if a customer with the same phoneNumber exists in dataMap
            if (dataMap.containsKey(newPhoneNumber)) {
                Customer existingCustomer = dataMap.get(newPhoneNumber);

                // If the IDs are the same
                boolean idDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getId().equals(newId));
                if (idDuplicate) {
                    // 2.1. If emails are the same
                    boolean emailDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getEmail().equals(newEmail));
                    if (emailDuplicate) {
                        existingCustomer.setName(newName); // 3.1: Only update name if email matches
                    } else {
                        existingCustomer.setEmail(newEmail); // 3.2: Update email and name if email differs
                        existingCustomer.setName(newName);
                    }
                } else { // 2.2. If IDs do not match
                    existingCustomer.setId(newId); // Update ID, email, and name

                    // Check email duplicate
                    boolean emailDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getEmail().equals(newEmail));
                    if (emailDuplicate) {
                        existingCustomer.setName(newName); // 3.3: Only update name if email matches
                    } else {
                        existingCustomer.setEmail(newEmail); // 3.4: Update email and name if email differs
                        existingCustomer.setName(newName);
                    }
                }
                dataMap.put(newPhoneNumber, existingCustomer);
            } else { // 1.2 If no customer with the same phoneNumber exists
                boolean idDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getId().equals(newId));
                boolean emailDuplicate = dataMap.values().stream().anyMatch(customer -> customer.getEmail().equals(newEmail));

                // 2.3 Check for duplicate id and log an error if found
                if (idDuplicate) {
                    errorLogger.logError("Duplicate ID " + newId + " for customer " + newCustomer);
                }
                // 2.4 Check for duplicate email and log an error if found
                else if (emailDuplicate) {
                    errorLogger.logError("Duplicate email " + newEmail + " for customer " + newCustomer);
                }
                // 2.5 If no duplicates are found, add the new customer
                else {
                    dataMap.put(newPhoneNumber, newCustomer);
                }
            }
        });
    }

    @Override
    public void editDataList(String editDataFilePath) {
        LinkedHashMap<String, Customer> editDataMap = dataLoader.loadDataToLinkedHashMap(editDataFilePath);
        dataMap.putAll(editDataMap);
        editDataMap.forEach((key, value) -> {
            if(dataMap.containsKey(key)) {
                dataMap.put(key, value);
            } else {
                errorLogger.logError("Not exist key " + key);
            }
        });
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