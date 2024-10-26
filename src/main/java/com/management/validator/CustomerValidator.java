package com.management.validator;

import com.management.model.Customer;

import java.util.Map;
import java.util.Set;

public class CustomerValidator implements ModelValidator<Customer> {
//    private Map<String, Set<String>> existingFields;

    @Override
    public void validate(Customer customer) throws Exception {
        // Kiểm tra các trường cơ bản
        if (customer.getId() == null || customer.getId().isEmpty()) {
            throw new Exception("Customer ID cannot be null or empty.");
        }
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new Exception("Customer name cannot be null or empty.");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new Exception("Customer email cannot be null or empty.");
        }
        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty()) {
            throw new Exception("Customer phone number cannot be null or empty.");
        }

//        // Kiểm tra trùng lặp ID
//        Set<String> customerIds = existingFields.computeIfAbsent("customerIds", k -> new HashSet<>());
//        if (customerIds.contains(customer.getId())) {
//            throw new Exception("Duplicate Customer ID: " + customer.getId());
//        } else {
//            customerIds.add(customer.getId());
//        }
//
//        // Kiểm tra trùng lặp email
//        Set<String> emails = existingFields.computeIfAbsent("emails", k -> new HashSet<>());
//        if (emails.contains(customer.getEmail())) {
//            throw new Exception("Duplicate Email: " + customer.getEmail());
//        } else {
//            emails.add(customer.getEmail());
//        }
//
//        // Kiểm tra trùng lặp số điện thoại
//        Set<String> phoneNumbers = existingFields.computeIfAbsent("phoneNumbers", k -> new HashSet<>());
//        if (phoneNumbers.contains(customer.getPhoneNumber())) {
//            throw new Exception("Duplicate Phone Number: " + customer.getPhoneNumber());
//        } else {
//            phoneNumbers.add(customer.getPhoneNumber());
//        }
    }

    @Override
    public void setExistingFields(Map<String, Set<String>> existingFields) {
//        this.existingFields = existingFields;
    }
}
