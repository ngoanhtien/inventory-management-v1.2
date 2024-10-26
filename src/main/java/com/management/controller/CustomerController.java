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

    public void addCustomers() {
        customerService.getData(BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.addDataList(BaseService.CUSTOMER_NEW_FILEPATH);
        customerService.writeData(BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void updateCustomers() {
        customerService.getData(BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.editDataList(BaseService.CUSTOMER_EDIT_FILEPATH);
        customerService.writeData(BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void deleteCustomers() {
        customerService.getData(BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.deleteDataList(BaseService.CUSTOMER_DELETE_FILEPATH);
        customerService.writeData(BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        customerController.deleteCustomers();
    }
}
