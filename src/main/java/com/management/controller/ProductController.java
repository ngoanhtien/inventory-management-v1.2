package com.management.controller;

import com.management.service.ProductService;
import com.management.utils.ErrorLogger;
import com.management.service.BaseService;

public class ProductController {
    private ProductService productService;
    private ErrorLogger errorLogger;

    public ProductController() {
        this.errorLogger = new ErrorLogger(BaseService.ERROR_FILEPATH);
        this.productService = new ProductService(errorLogger);
    }

    public void loadProducts() {
        productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        productService.writeData(BaseService.PRODUCT_OUTPUT_FILEPATH);
    }

    public void addProducts() {
        productService.getData(BaseService.PRODUCT_INPUT_FILEPATH);
        productService.addDataList(BaseService.PRODUCT_NEW_FILEPATH);
        productService.writeData(BaseService.PRODUCT_OUTPUT_FILEPATH);
    }

    public static void main(String[] args) {
        ProductController productController = new ProductController();
        productController.addProducts();
    }
}
