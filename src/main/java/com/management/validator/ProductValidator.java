package com.management.validator;

import com.management.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductValidator implements ModelValidator<Product> {
    Set<String> existingIds = new HashSet<>();
    public void validate(Product product, List<Set<String>> existFields) throws Exception {
//        if(existFields.size() == 0) {
//            existingIds = new HashSet<String>();
//            existFields.add(existingIds);
//        }

        String productId = product.getId();
        String productName = product.getName();
        double productPrice = product.getPrice();
        int stockAvailable = product.getStockAvailable();

        if ("null".equals(productId) || productId.isEmpty()) {
            throw new Exception("Product ID cannot be null or empty: " + product.toString());
        } else if (existingIds.contains(productId)) {
            throw new Exception("Product already exists: " + product.toString());
        } else {
            existingIds.add(productId);
        }
        if (productName == null || productName.isEmpty()) {
            throw new Exception("Product name cannot be null or empty: " + product.toString());
        }
        if (productPrice < 0) {
            throw new Exception("Product price must be positive: " + product.toString());
        }
        if (stockAvailable < 0) {
            throw new Exception("Stock available must be non-negative: " + product.toString());
        }
    }
}
