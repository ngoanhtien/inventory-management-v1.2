package com.management.validator;

import com.management.model.Customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class CustomerValidator implements ModelValidator<Customer> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^@ ]+@[^@ ]+\\.[^@ ]+");
    private static final Pattern PHONE_PATTERN = Pattern.compile("[0-9\\-\\+]{9,15}");

    Set<String> existingIds;
    Set<String> existingEmails;
    Set<String> existingPhones;

    public void validate(Customer customer, List<Set<String>> existFields) throws Exception {
        if(existFields.size() == 0) {
            existingIds = new HashSet<>();
            existingEmails = new HashSet<>();
            existingPhones = new HashSet<>();
            existFields.add(existingIds);
            existFields.add(existingEmails);
            existFields.add(existingPhones);
        }

        String id = customer.getId();
        String name = customer.getName();
        String email = customer.getEmail();
        String phoneNumber = customer.getPhoneNumber();

        if (id == null || id.isEmpty()) {
            throw new Exception("Customer ID is required.");
        } else if (existingIds.contains(id)) {
            throw new Exception("Duplicate Customer ID: " + id);
        } else {
            existingIds.add(id);
        }

        if (name == null || name.isEmpty()) {
            throw new Exception("Customer Name cannot be empty. ID: " + id);
        }

        if (email == null || !isValidEmail(email)) {
            throw new Exception("Invalid Email format. ID: " + id);
        } else if (existingEmails.contains(email)) {
            throw new Exception("Duplicate Email: " + email);
        } else {
            existingEmails.add(email);
        }

        if (phoneNumber == null || !isValidPhoneNumber(phoneNumber)) {
            throw new Exception("Invalid PhoneNumber format. ID: " + id);
        } else if (existingPhones.contains(phoneNumber)) {
            throw new Exception("Duplicate PhoneNumber: " + phoneNumber);
        } else {
            existingPhones.add(phoneNumber);
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
