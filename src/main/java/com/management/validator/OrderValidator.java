package com.management.validator;

import com.management.model.Order;
import com.management.utils.ErrorLogger;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderValidator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public void validate(Order order, ErrorLogger errorLogger, List<Set<String>> existFields) {
        Set<String> existingIds = new HashSet<>();
        Set<String> customerIds = new HashSet<>();
        Set<String> productIds = new HashSet<>();
        existFields.add(existingIds);
        existFields.add(customerIds);
        existFields.add(productIds);

        String id = order.getId();
        String customerId = order.getCustomerId();
        Map<String, Integer> productQuantities = order.getProductQuantities();
        OffsetDateTime orderDate = order.getOrderDate();

        if (id == null || id.isEmpty()) {
            errorLogger.logError("Order ID is required.");
        } else if (existingIds.contains(id)) {
            errorLogger.logError("Duplicate Order ID: " + id);
        } else {
            existingIds.add(id);
        }

        if (customerId == null || !customerIds.contains(customerId)) {
            errorLogger.logError("Invalid CustomerID: " + customerId + " in Order ID: " + id);
        }

        if (productQuantities == null || productQuantities.isEmpty()) {
            errorLogger.logError("ProductQuantities cannot be empty in Order ID: " + id);
        } else {
            for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
                String productId = entry.getKey();
                int quantity = entry.getValue();

                if (!productIds.contains(productId)) {
                    errorLogger.logError("ProductID " + productId + " does not exist in Order ID: " + id);
                }

                if (quantity <= 0) {
                    errorLogger.logError("Quantity for ProductID " + productId + " must be > 0 in Order ID: " + id);
                }
            }
        }

        try {
            if (order.getOrderDate() == null) {
                errorLogger.logError("OrderDate is required in Order ID: " + id);
            } else {
                DATE_FORMATTER.parse(order.getOrderDate().toString());
            }
        } catch (DateTimeParseException e) {
            errorLogger.logError("Invalid orderDate format in Order ID: " + id);
        }
    }
}
