package com.management.service;

import com.management.model.Product;
import com.management.parser.ProductParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ProductValidator;

import java.util.ArrayList;
import java.util.List;

public class ProductService extends BaseService<Product> {

    private List<Product> products = new ArrayList<>();

    public ProductService(ErrorLogger errorLogger) {
        super(new ProductParser(), new ProductValidator(), errorLogger);
    }

    @Override
    public void getData(String inputFilePath) {
        products = dataLoader.loadData(inputFilePath);
    }

//    public List<Product> getDataWithoutValidation(){
//
//    }

    @Override
    public void addDataList(String newDataFilePath) {

    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(products, outputFilePath);
    }
}
