package com.management.controller;

import com.management.model.Customer;
import com.management.model.Product;
import com.management.service.CustomerService;
import com.management.service.OrderService;
import com.management.service.ProductService;
import com.management.utils.ErrorLogger;
import com.management.service.BaseService;
import java.util.List;

public class OrderController {
    private OrderService orderService;
    private ErrorLogger errorLogger;

    public OrderController(List<Product> productList, List<Customer> customerList) {
        this.errorLogger = new ErrorLogger(BaseService.ERROR_FILEPATH);
        this.orderService = new OrderService(errorLogger, customerList, productList);
    }

    public void loadOrders(){
        orderService.getData(BaseService.ORDER_INPUT_FILEPATH);
        orderService.writeData(BaseService.ORDER_OUTPUT_FILEPATH);
    }

    public static void main(String[] args) {
        ErrorLogger errorLogger = new ErrorLogger(BaseService.ERROR_FILEPATH);
        ProductService productService = new ProductService(errorLogger);
        CustomerService customerService = new CustomerService(errorLogger);
        List<Product> productList = productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        List<Customer> customerList = customerService.getData(BaseService.CUSTOMER_INPUT_FILEPATH);
        OrderController orderController = new OrderController(productList, customerList);
        orderController.loadOrders();
    }
}
