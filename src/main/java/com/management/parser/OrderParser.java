package com.management.parser;

import com.management.model.Order;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class OrderParser implements ModelParser<Order> {

    @Override
    public Order parse(String line) throws Exception {
        String[] data = line.split(ModelParser.SPLIT_CHARACTER);
        if (line.isBlank()){
            throw new Exception("Empty line");
        } else if (data.length != 4) {
            throw new Exception("Invalid order data format: " + line);
        }
        String id = data[0].trim();
        String customerId = data[1].trim();
        String productsString = data[2].trim();
        String dateString = data[3].trim();

        Map<String, Integer> productQuantities = getStringIntegerMap(productsString);

        OffsetDateTime orderDate;
        try {
            orderDate = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (Exception e) {
            throw new Exception("Invalid date format: " + dateString);
        }

        return new Order(id, customerId, productQuantities, orderDate);
    }

    private static Map<String, Integer> getStringIntegerMap(String productsString) throws Exception {
        Map<String, Integer> productQuantities = new HashMap<>();
        if (!productsString.isEmpty()) {
            String[] productEntries = productsString.split(";");
            for (String entry : productEntries) {
                String[] productQuantity = entry.split(":");
                if (productQuantity.length != 2) {
                    throw new Exception("Invalid product quantity format: " + entry);
                }
                String productId = productQuantity[0].trim();
                int quantity;
                try {
                    quantity = Integer.parseInt(productQuantity[1].trim());
                } catch (NumberFormatException e) {
                    throw new Exception("Invalid quantity value: " + productQuantity[1]);
                }
                productQuantities.put(productId, quantity);
            }
        }
        return productQuantities;
    }
}
