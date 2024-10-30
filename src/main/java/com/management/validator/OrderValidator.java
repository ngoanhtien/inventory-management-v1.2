package com.management.validator;

import com.management.model.Order;
import com.management.model.Customer;
import com.management.model.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderValidator implements ModelValidator<Order> {
    private Map<String, Product> productList;
    private Map<String, Customer> customerList;

    public OrderValidator(Map<String, Product> productList,
                          Map<String, Customer> customerList) {
        this.productList = productList;
        this.customerList = customerList;
    }

    @Override
    public void validate(Order order) throws Exception {
        if (order.getId() == null || order.getId().isEmpty()) {
            throw new Exception("Order ID cannot be null or empty.");
        }

        if (order.getCustomerId() == null || order.getCustomerId().isEmpty()) {
            throw new Exception("Customer ID cannot be null or empty.");
        } else if (!isValidCustomerId(order.getCustomerId())) {
            throw new Exception("Customer ID does not exist: " + order.getCustomerId());
        }

        if (order.getProductQuantities() == null || order.getProductQuantities().isEmpty()) {
            throw new Exception("Order must contain at least one product.");
        } else {
            for (Map.Entry<String, Integer> entry : order.getProductQuantities().entrySet()) {
                String productId = entry.getKey();
                int quantity = entry.getValue();

                if (!isValidProductId(productId)) {
                    throw new Exception("Product ID does not exist: " + productId + "in order: " + order.getId());
                }
                if (quantity <= 0) {
                    throw new Exception("Quantity must be greater than 0 for product ID: " + order.toString());
                }
            }
        }

        if (order.getOrderDate() == null) {
            throw new Exception("Order date cannot be null.");
        }
    }

    private boolean isValidCustomerId(String customerId) {
        for (Customer customer : customerList.values()) {
            if (customerId.equals(customer.getId())) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidProductId(String productId) {
        return productList.containsKey(productId);
    }
}
