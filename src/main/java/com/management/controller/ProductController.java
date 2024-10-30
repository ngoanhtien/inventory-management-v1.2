package com.management.controller;

import com.management.model.Customer;
import com.management.model.Order;
import com.management.model.Product;
import com.management.service.CustomerService;
import com.management.service.OrderService;
import com.management.service.ProductService;
import com.management.utils.ErrorLogger;
import com.management.service.BaseService;

import java.io.File;
import java.util.Map;

public class ProductController{
    private ProductService productService;
    private CustomerService customerService;
    private OrderService orderService;
    private ErrorLogger errorLogger;
    private Map<String, Product> products;
    private Map<String, Customer> customers;
    private String folderPath;

    public ProductController(String folderPath) {
        this.folderPath = folderPath;
        this.errorLogger = new ErrorLogger(folderPath + File.separator + BaseService.ERROR_FILEPATH);
        this.productService = new ProductService(errorLogger);
        this.customerService = new CustomerService(errorLogger);
        this.products = productService.getData(folderPath + File.separator + BaseService.PRODUCT_INPUT_FILEPATH);
        this.customers = customerService.getData(folderPath + File.separator + BaseService.CUSTOMER_INPUT_FILEPATH);
        this.orderService = new OrderService(errorLogger, products, customers);
    }

    public void loadProducts() {
        productService.getData(folderPath + File.separator + BaseService.PRODUCT_INPUT_FILEPATH);
        productService.writeData(folderPath + File.separator + BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void addProducts() {
        productService.getData(folderPath + File.separator + BaseService.PRODUCT_INPUT_FILEPATH);
        productService.addDataList(folderPath + File.separator + BaseService.PRODUCT_NEW_FILEPATH);
        productService.writeData(folderPath + File.separator + BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void updateProducts() {
        productService.getData(folderPath + File.separator + BaseService.PRODUCT_INPUT_FILEPATH);
        productService.editDataList(folderPath + File.separator + BaseService.PRODUCT_EDIT_FILEPATH);
        productService.writeData(folderPath + File.separator + BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void deleteProducts() {
        productService.getData(folderPath + File.separator + BaseService.PRODUCT_INPUT_FILEPATH);
        productService.deleteDataList(folderPath + File.separator + BaseService.PRODUCT_DELETE_FILEPATH);
        productService.writeData(folderPath + File.separator + BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void top3ProductBestSeller(){
        Map<String, Order> o = orderService.getData(folderPath + File.separator + BaseService.ORDER_INPUT_FILEPATH);
        productService.findTop3ProductsByOrderQuantity(o, products);
        productService.writeData(folderPath + File.separator + BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }
}
