package com.management.model;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Order implements CSVConvertible, Identifiable<String> {
    private String id;
    private String customerId;
    private Map<String, Integer> productQuantities;
    private OffsetDateTime orderDate;

    public Order() {}

    public Order(String id, String customerId, Map<String, Integer> productQuantities, OffsetDateTime orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.productQuantities = productQuantities;
        this.orderDate = orderDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<String, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public OffsetDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(OffsetDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productQuantities=" + productQuantities +
                ", orderDate=" + orderDate;
    }

    @Override
    public String toCSV() {
        StringBuilder products = new StringBuilder();
        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            if (!products.isEmpty()) {
                products.append(";");
            }
            products.append(entry.getKey()).append(":").append(entry.getValue());
        }
        return String.format("%s,%s,%s,%s", id, customerId, products.toString(), orderDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
