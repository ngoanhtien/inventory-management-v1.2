package com.management.service;

import com.management.dal.DataLoader;
import com.management.dal.DataWriter;
import com.management.model.CSVConvertible;
import com.management.parser.ModelParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

import java.util.List;

public abstract class BaseService<T extends CSVConvertible> {
    public static final String ERROR_FILEPATH = "src\\main\\resources\\data\\error.output.txt";

    public static final String PRODUCT_OUTPUT_FILEPATH = "src\\main\\resources\\data\\output\\product.output.csv";
    public static final String PRODUCT_INPUT_FILEPATH = "src\\main\\resources\\data\\origin\\product.origin.csv";
    public static final String PRODUCT_NEW_FILEPATH = "src\\main\\resources\\data\\new\\products.new.csv";

    public static final String CUSTOMER_OUTPUT_FILEPATH = "src\\main\\resources\\data\\output\\customer.output.csv";
    public static final String CUSTOMER_INPUT_FILEPATH = "src\\main\\resources\\data\\origin\\customer.origin.csv";

    public static final String ORDER_OUTPUT_FILEPATH = "src\\main\\resources\\data\\output\\order.output.csv";
    public static final String ORDER_INPUT_FILEPATH = "src\\main\\resources\\data\\origin\\order.origin.csv";

    protected DataLoader<T> dataLoader;
    protected DataWriter<T> dataWriter;
    protected ErrorLogger errorLogger;

    public BaseService(ModelParser<T> parser, ModelValidator<T> validator, ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
        this.dataLoader = new DataLoader<>(parser, validator, errorLogger);
        this.dataWriter = new DataWriter<>();
    }

    public abstract List<T> getData(String inputFilePath);

    public abstract List<T> addDataList(String newDataFilePath);

    public abstract void writeData(String outputFilePath);
}
