package com.management.validator;

import com.management.model.Product;

public class ProductValidator implements ModelValidator<Product> {

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
    }
}
