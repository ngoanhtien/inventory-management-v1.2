package com.management.controller;

import com.management.model.Customer;
import com.management.model.Order;
import com.management.model.Product;
import com.management.service.CustomerService;
import com.management.service.OrderService;
import com.management.service.ProductService;
import com.management.utils.ErrorLogger;
import com.management.service.BaseService;

import java.util.LinkedHashMap;
import java.util.List;

public class ProductController {
    private ProductService productService;
    private CustomerService customerService;
    private OrderService orderService;
    private ErrorLogger errorLogger;

    public ProductController() {
        this.errorLogger = new ErrorLogger(BaseService.ERROR_FILEPATH);
        this.productService = new ProductService(errorLogger);
    }

    public void loadProducts() {
        productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        productService.writeData(BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void addProducts() {
        productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        productService.addDataList(BaseService.PRODUCT_NEW_FILEPATH);
        productService.writeData(BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void updateProducts() {
        productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        productService.editDataList(BaseService.PRODUCT_EDIT_FILEPATH);
        productService.writeData(BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void deleteProducts() {
        productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        productService.deleteDataList(BaseService.PRODUCT_DELETE_FILEPATH);
        productService.writeData(BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void top3Product(){
        CustomerService customerService = new CustomerService(errorLogger);
        LinkedHashMap<String, Product> p = productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        LinkedHashMap<String, Customer> c = customerService.getData(BaseService.CUSTOMER_INPUT_FILEPATH);
        OrderService a = new OrderService(errorLogger, p, c);
        LinkedHashMap<String, Order> o = a.getData(BaseService.ORDER_INPUT_FILEPATH);
        productService.findTop3ProductsByOrderQuantity(o, p);
        productService.writeData(BaseService.PRODUCT_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public static void main(String[] args) {
        ProductController productController = new ProductController();
        productController.top3Product();
    }
}
