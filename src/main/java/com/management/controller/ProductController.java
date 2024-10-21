package com.management.controller;

import com.management.service.ProductService;

public class ProductController {
    public static final String OUTPUT_FILEPATH = "D:\\Downloads\\NgoAnhTien_JavaFresher_Mock1_V1.2\\src\\main\\resources\\data\\product.output.csv";
    public static final String INPUT_FILEPATH = "D:\\Downloads\\NgoAnhTien_JavaFresher_Mock1_V1.2\\src\\main\\resources\\data\\product.origin.csv";

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void processProductData() {
        productService.getData(INPUT_FILEPATH);
        productService.writeData(OUTPUT_FILEPATH);
    }
}
