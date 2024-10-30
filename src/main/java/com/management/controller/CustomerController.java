package com.management.controller;

import com.management.service.BaseService;
import com.management.service.CustomerService;
import com.management.utils.ErrorLogger;

import java.io.File;

public class CustomerController {
    private CustomerService customerService;
    private ErrorLogger errorLogger;
    private String folderPath;

    public CustomerController(String folderPath) {
        this.folderPath = folderPath;
        this.errorLogger = new ErrorLogger(folderPath + File.separator + BaseService.ERROR_FILEPATH);
        this.customerService = new CustomerService(errorLogger);
    }

    public void loadCustomers() {
        customerService.getData(folderPath + File.separator + BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.writeData(folderPath + File.separator + BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void addCustomers() {
        customerService.getData(folderPath + File.separator + BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.addDataList(folderPath + File.separator + BaseService.CUSTOMER_NEW_FILEPATH);
        customerService.writeData(folderPath + File.separator + BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void updateCustomers() {
        customerService.getData(folderPath + File.separator + BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.editDataList(folderPath + File.separator + BaseService.CUSTOMER_EDIT_FILEPATH);
        customerService.writeData(folderPath + File.separator + BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }

    public void deleteCustomers() {
        customerService.getData(folderPath + File.separator + BaseService.CUSTOMER_INPUT_FILEPATH);
        customerService.deleteDataList(folderPath + File.separator + BaseService.CUSTOMER_DELETE_FILEPATH);
        customerService.writeData(folderPath + File.separator + BaseService.CUSTOMER_OUTPUT_FILEPATH);
        errorLogger.close();
    }
}
