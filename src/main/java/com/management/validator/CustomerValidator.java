package com.management.validator;

import com.management.model.Customer;

public class CustomerValidator implements ModelValidator<Customer> {

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
    }
}
