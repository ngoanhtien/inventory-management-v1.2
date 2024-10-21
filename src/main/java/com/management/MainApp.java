package com.management;

import com.management.controller.ProductController;
import com.management.service.ProductService;
import com.management.utils.ErrorLogger;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {
        final String ERROR_FILEPATH = "D:\\Downloads\\NgoAnhTien_JavaFresher_Mock1_V1.2\\src\\main\\resources\\data\\error.output.csv";
        ErrorLogger errorLogger = null;
        try {
            errorLogger = new ErrorLogger(ERROR_FILEPATH);
            ProductService productService = new ProductService(errorLogger);
            ProductController productController = new ProductController(productService);
            productController.processProductData();
            System.out.println();
        } catch (IOException e) {
            errorLogger.logError("Error reading file" + e.getMessage());
        } finally {
            errorLogger.close();
        }
    }
}