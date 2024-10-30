package com.management.service;

import com.management.dal.DataLoader;
import com.management.dal.DataWriter;
import com.management.model.CSVConvertible;
import com.management.model.Identifiable;
import com.management.parser.ModelParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseService<K, T extends Identifiable<K> & CSVConvertible> {
    public static final String ERROR_FILEPATH = "src\\main\\resources\\data\\OutputFolder\\error.output.txt";

    public static final String PRODUCT_OUTPUT_FILEPATH = "src\\main\\resources\\data\\OutputFolder\\products.output.csv";
    public static final String PRODUCT_INPUT_FILEPATH = "src\\main\\resources\\data\\InputFolder\\products.origin.csv";
    public static final String PRODUCT_NEW_FILEPATH = "src\\main\\resources\\data\\InputFolder\\products.new.csv";
    public static final String PRODUCT_EDIT_FILEPATH = "src\\main\\resources\\data\\InputFolder\\products.edit.csv";
    public static final String PRODUCT_DELETE_FILEPATH = "src\\main\\resources\\data\\InputFolder\\products.delete.csv";
    public static final String PRODUCT_SEARCH_FILEPATH = "src\\main\\resources\\data\\InputFolder\\productIds.search.csv";

    public static final String CUSTOMER_OUTPUT_FILEPATH = "src\\main\\resources\\data\\OutputFolder\\customers.output.csv";
    public static final String CUSTOMER_INPUT_FILEPATH = "src\\main\\resources\\data\\InputFolder\\customers.origin.csv";
    public static final String CUSTOMER_NEW_FILEPATH = "src\\main\\resources\\data\\InputFolder\\customers.new.csv";
    public static final String CUSTOMER_EDIT_FILEPATH = "src\\main\\resources\\data\\InputFolder\\customers.edit.csv";
    public static final String CUSTOMER_DELETE_FILEPATH = "src\\main\\resources\\data\\InputFolder\\customers.delete.csv";

    public static final String ORDER_OUTPUT_FILEPATH = "src\\main\\resources\\data\\OutputFolder\\orders.output.csv";
    public static final String ORDER_INPUT_FILEPATH = "src\\main\\resources\\data\\InputFolder\\orders.origin.csv";
    public static final String ORDER_NEW_FILEPATH = "src\\main\\resources\\data\\InputFolder\\orders.new.csv";
    public static final String ORDER_EDIT_FILEPATH = "src\\main\\resources\\data\\InputFolder\\orders.edit.csv";
    public static final String ORDER_DELETE_FILEPATH = "src\\main\\resources\\data\\InputFolder\\orders.delete.csv";

    protected DataLoader<K, T> dataLoader;
    protected DataWriter<T> dataWriter;
    protected ErrorLogger errorLogger;

    protected Map<K, T> dataMap;

    public BaseService(ModelParser<T> parser, ModelValidator<T> validator, ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
        this.dataLoader = new DataLoader<>(parser, validator, errorLogger);
        this.dataWriter = new DataWriter<>();
        dataMap = new LinkedHashMap<>();
    }

    public abstract Map<K, T> getData(String inputFilePath);

    public abstract void addDataList(String newDataFilePath);

    public abstract void editDataList(String editDataFilePath);

    public abstract void deleteDataList(String deleteDataFilePath);

    public abstract void writeData(String outputFilePath);
}
