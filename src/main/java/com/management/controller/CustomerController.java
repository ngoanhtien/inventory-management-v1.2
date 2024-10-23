package com.management.controller;

import com.management.service.BaseService;
import com.management.service.CustomerService;
import com.management.utils.ErrorLogger;

public class CustomerController {
    private CustomerService customerService;
    private ErrorLogger errorLogger;

    public CustomerController() {
        this.errorLogger = new ErrorLogger(BaseService.ERROR_FILEPATH);
        this.customerService = new CustomerService(errorLogger);
    }

    public void loadCustomers() {
        customerService.getData(BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.writeData(BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        customerController.loadCustomers();
    }
}
