package com.management.model;

public class Product implements CSVConvertible, Identifiable<String>{
    private String id;
    private String name;
    private double price;
    private int stockAvailable;

    public Product() {}

    public Product(String id, String name, double price, int stockAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    @Override
    public String getId() { return this.id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockAvailable=" + stockAvailable +
                '}';
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%.2f,%d", id, name, price, stockAvailable);
    }
}

