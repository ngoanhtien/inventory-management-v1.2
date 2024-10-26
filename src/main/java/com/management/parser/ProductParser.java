package com.management.parser;

import com.management.model.Product;

public class ProductParser implements ModelParser<Product> {
    @Override
    public Product parse(String line) throws Exception {
        String[] data = line.split(",");

        if (line.isBlank()){
            throw new Exception("Empty line");
        } else if (data.length != 4) {
            throw new Exception("Invalid product data format: " + line);
        }
        String id = data[0].trim();
        String name = data[1].trim();
        double price;
        int stockAvailable;

        try {
            price = Double.parseDouble(data[2].trim());
        } catch (NumberFormatException e) {
            throw new Exception("Invalid price format in product data: " + line);
        }

        try {
            stockAvailable = Integer.parseInt(data[3].trim());
        } catch (NumberFormatException e) {
            throw new Exception("Invalid stockAvailable format in product data: " + line);
        }
        return new Product(id, name, price, stockAvailable);
    }
}
