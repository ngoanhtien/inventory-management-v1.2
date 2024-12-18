package com.management.parser;

import com.management.model.Customer;

public class CustomerParser implements ModelParser<Customer> {
    @Override
    public Customer parse(String line) throws Exception {
        String[] data = line.split(ModelParser.SPLIT_CHARACTER);

        if (line.isBlank()){
            throw new Exception("Empty line");
        } else if (data.length != 4) {
            throw new Exception("Invalid customer data format: " + line);
        }

        String id = data[0].trim();
        String name = data[1].trim();
        String email = data[2].trim();
        String phoneNumber = data[3].trim();
        return new Customer(id, name, email, phoneNumber);
    }
}
