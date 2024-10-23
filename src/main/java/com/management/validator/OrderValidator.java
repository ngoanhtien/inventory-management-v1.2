package com.management.validator;

import com.management.model.Order;
import com.management.model.Customer;
import com.management.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderValidator implements ModelValidator<Order> {
    private Map<String, Set<String>> existingFields;
    private List<Customer> customerList;
    private List<Product> productList;

    public OrderValidator(List<Customer> customerList, List<Product> productList) {
        this.customerList = customerList;
        this.productList = productList;
    }

    @Override
    public void validate(Order order) throws Exception {
        // Kiểm tra ID của Order
        if (order.getId() == null || order.getId().isEmpty()) {
            throw new Exception("Order ID cannot be null or empty.");
        }
        // Kiểm tra trùng lặp ID Order
        Set<String> orderIds = existingFields.computeIfAbsent("orderIds", k -> new java.util.HashSet<>());
        if (orderIds.contains(order.getId())) {
            throw new Exception("Duplicate Order ID: " + order.getId());
        } else {
            orderIds.add(order.getId());
        }

        // Kiểm tra customerId
        if (order.getCustomerId() == null || order.getCustomerId().isEmpty()) {
            throw new Exception("Customer ID cannot be null or empty.");
        } else if (!isValidCustomerId(order.getCustomerId())) {
            throw new Exception("Customer ID does not exist: " + order.getCustomerId());
        }

        // Kiểm tra productQuantities
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

        // Kiểm tra ngày đặt hàng
        if (order.getOrderDate() == null) {
            throw new Exception("Order date cannot be null.");
        }
    }

    @Override
    public void setExistingFields(Map<String, Set<String>> existingFields) {
        this.existingFields = existingFields;
    }

    // Phương thức kiểm tra customerId có tồn tại hay không
    private boolean isValidCustomerId(String customerId) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    // Phương thức kiểm tra productId có tồn tại hay không
    private boolean isValidProductId(String productId) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
}
