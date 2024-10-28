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
import java.util.Map;

public class OrderController {
    private OrderService orderService;
    protected ProductService productService;
    private ErrorLogger errorLogger;
//    private Map<String, String> customerIdToPhoneNumberMap;


    public OrderController(LinkedHashMap<String, Product> productList,
                           LinkedHashMap<String, Customer> customerList) {
        this.errorLogger = new ErrorLogger(BaseService.ERROR_FILEPATH);
        this.orderService = new OrderService(errorLogger, productList, customerList);
    }

    public void loadOrders(){
        orderService.getData(BaseService.ORDER_INPUT_FILEPATH);
        orderService.writeData(BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void addOrders() {
        orderService.getData(BaseService.ORDER_INPUT_FILEPATH);
        orderService.addDataList(BaseService.ORDER_NEW_FILEPATH);
        orderService.writeData(BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void updateOrders() {
        orderService.getData(BaseService.ORDER_INPUT_FILEPATH);
        orderService.editDataList(BaseService.ORDER_EDIT_FILEPATH);
        orderService.writeData(BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void deleteOrders() {
        orderService.getData(BaseService.ORDER_INPUT_FILEPATH);
        orderService.deleteDataList(BaseService.ORDER_DELETE_FILEPATH);
        orderService.writeData(BaseService.ORDER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void searchOrderByProductId(){

    }

    public static void main(String[] args) {
        ErrorLogger errorLogger = new ErrorLogger(BaseService.ERROR_FILEPATH);
        ProductService productService = new ProductService(errorLogger);
        CustomerService customerService = new CustomerService(errorLogger);
        LinkedHashMap<String, Product> productList = productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        LinkedHashMap<String, Customer> customerList = customerService.getData(BaseService.CUSTOMER_INPUT_FILEPATH);
//        Map<String, String> customerIdToPhoneNumberMap = new LinkedHashMap<>();
        OrderController orderController = new OrderController(productList, customerList);
//        orderController.deleteOrders();
        orderController.searchOrderByProductId();
    }
}
