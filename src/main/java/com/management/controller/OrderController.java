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
import java.util.Set;

public class OrderController {
    private OrderService orderService;
    private ErrorLogger errorLogger;
    private ProductService productService;
    private CustomerService customerService;
    Map<String, Product> productList;
    Map<String, Customer> customerList;
    private String folderPath;

    public OrderController(String folderPath){
        this.folderPath = folderPath;
        this.errorLogger = new ErrorLogger(folderPath + File.separator + BaseService.ERROR_FILEPATH);
        this.productService = new ProductService(errorLogger);
        this.customerService = new CustomerService(errorLogger);
        this.productList = productService.getData(folderPath + File.separator + BaseService.PRODUCT_INPUT_FILEPATH);
        this.customerList = customerService.getData(folderPath + File.separator + BaseService.CUSTOMER_INPUT_FILEPATH);
        this.orderService = new OrderService(errorLogger, productList, customerList);
    } ;

    public void loadOrders(){
        orderService.getData(folderPath + File.separator + BaseService.ORDER_INPUT_FILEPATH);
        orderService.writeData(folderPath + File.separator + BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void addOrders() {
        orderService.getData(folderPath + File.separator + BaseService.ORDER_INPUT_FILEPATH);
        orderService.addDataList(folderPath + File.separator + BaseService.ORDER_NEW_FILEPATH);
        orderService.writeData(folderPath + File.separator + BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void updateOrders() {
        orderService.getData(folderPath + File.separator + BaseService.ORDER_INPUT_FILEPATH);
        orderService.editDataList(folderPath + File.separator + BaseService.ORDER_EDIT_FILEPATH);
        orderService.writeData(folderPath + File.separator + BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void deleteOrders() {
        orderService.getData(folderPath + File.separator + BaseService.ORDER_INPUT_FILEPATH);
        orderService.deleteDataList(folderPath + File.separator + BaseService.ORDER_DELETE_FILEPATH);
        orderService.writeData(folderPath + File.separator + BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void searchOrderByProductId(){
        OrderService newOrderService = new OrderService(errorLogger, productList, customerList);
        Map<String, Order> orderMap = newOrderService.getData(folderPath + File.separator + BaseService.ORDER_INPUT_FILEPATH);
        Set<String> productIds = productService.getProductIds();
        orderService.hanldleFindOrdersByProductIds(orderMap, productIds);
        orderService.writeData(folderPath + File.separator + BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }
}
