package com.management.parser;

import com.management.model.Customer;

public class CustomerParser implements ModelParser<Customer> {
    @Override
    public Customer parse(String line) throws Exception {
        String[] data = line.split(",");
        if (data.length != 4) {
            throw new Exception("Invalid customer data format: " + line);
        }

        String id = data[0].trim();
        String name = data[1].trim();
        String email = data[2].trim();
        String phoneNumber = data[3].trim();

        // Validate email format
        if (!email.contains("@")) {
            throw new Exception("Invalid email format in customer data: " + line);
        }

        return new Customer(id, name, email, phoneNumber);
    }
}
