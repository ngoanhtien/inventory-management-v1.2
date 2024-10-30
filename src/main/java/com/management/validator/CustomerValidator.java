package com.management.validator;

import com.management.model.Customer;

import java.util.regex.Pattern;

public class CustomerValidator implements ModelValidator<Customer> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^@ ]+@[^@ ]+\\.[^@ ]+");
    private static final Pattern PHONE_PATTERN = Pattern.compile("[0-9\\-\\+]{9,15}");

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
        } else if (!isValidEmail(customer.getEmail())) {
            throw new Exception("Customer email is not a valid email address.");
        }
        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty()) {
            throw new Exception("Customer phone number cannot be null or empty.");
        } else if (!isValidPhoneNumber(customer.getPhoneNumber())) {
            throw new Exception("Customer phone number is not a valid phone number.");
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
