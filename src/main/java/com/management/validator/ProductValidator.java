package com.management.validator;

import com.management.model.Product;

import java.util.Map;
import java.util.Set;

public class ProductValidator implements ModelValidator<Product> {
//    private Map<String, Set<String>> existingFields;

    @Override
    public void validate(Product product) throws Exception {
        // Kiểm tra các trường cơ bản
        if (product.getId() == null || product.getId().isEmpty()) {
            throw new Exception("Product ID cannot be null or empty.");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new Exception("Product name cannot be null or empty.");
        }
        if (product.getPrice() < 0) {
            throw new Exception("Product price must be non-negative.");
        }
        if (product.getStockAvailable() < 0) {
            throw new Exception("Stock available must be non-negative.");
        }

        // Kiểm tra trùng lặp ID
//        Set<String> productIds = existingFields.computeIfAbsent("productIds", k -> new HashSet<>());
//        if (productIds.contains(product.getId())) {
//            throw new Exception("Duplicate Product ID: " + product.getId());
//        } else {
//            productIds.add(product.getId());
//        }
    }

    @Override
    public void setExistingFields(Map<String, Set<String>> existingFields) {
    }
}
