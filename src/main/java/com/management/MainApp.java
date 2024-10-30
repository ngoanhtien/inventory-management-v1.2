package com.management;

import com.management.controller.CustomerController;
import com.management.controller.OrderController;
import com.management.controller.ProductController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainApp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide fully 2 arguments: function code and folder path");
            return;
        }
        String functionCode = args[0];
        String folderPath = args[1];

        Path processingFolderPath = Paths.get(folderPath);
        if (!Files.exists(processingFolderPath)) {
            System.out.println("Folder path is not exist: " + folderPath);
            return;
        }

//        String functionCode = "1";
//        String folderPath = "D:\\Downloads\\NgoAnhTien_JavaFresher_Mock1_V1.2";

        ProductController productController = new ProductController(folderPath);
        CustomerController customerController = new CustomerController(folderPath);
        OrderController orderController = new OrderController(folderPath);
        switch (functionCode) {
            case "1":
                productController.loadProducts();
                customerController.loadCustomers();
                orderController.loadOrders();
                break;
            case "2.1": productController.addProducts(); break;
            case "2.2": productController.updateProducts(); break;
            case "2.3": productController.deleteProducts(); break;
            case "3.1": customerController.deleteCustomers(); break;
            case "3.2": customerController.addCustomers(); break;
            case "3.3": customerController.updateCustomers(); break;
            case "4.1": orderController.addOrders(); break;
            case "4.2": orderController.updateOrders(); break;
            case "4.3": orderController.deleteOrders(); break;
            case "5.1": productController.top3ProductBestSeller(); break;
            case "5.2": orderController.searchOrderByProductId(); break;
        }
    }
}