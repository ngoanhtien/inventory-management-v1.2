package com.management;

import com.management.controller.ProductController;

public class MainApp {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        productController.loadProducts();
    }
}