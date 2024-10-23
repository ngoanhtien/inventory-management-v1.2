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
    public List<Product> getData(String inputFilePath) {
        products = dataLoader.loadData(inputFilePath);
        return products;
    }

    @Override
    public List<Product> addDataList(String newDataFilePath) {
        List<Product> newProducts = dataLoader.loadData(newDataFilePath);
        products.addAll(newProducts);
        return products;
    }

    @Override
    public void writeData(String outputFilePath) {
        dataWriter.writeData(products, outputFilePath);
    }
}
