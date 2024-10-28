package com.management.service;

import com.management.dal.DataLoader;
import com.management.dal.DataWriter;
import com.management.model.CSVConvertible;
import com.management.model.Identifiable;
import com.management.parser.ModelParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

import java.util.LinkedHashMap;

public abstract class BaseService<K, T extends Identifiable<K> & CSVConvertible> {
    public static final String ERROR_FILEPATH = "src\\main\\resources\\data\\error.output.txt";

    public static final String PRODUCT_OUTPUT_FILEPATH = "src\\main\\resources\\data\\output\\product.output.csv";
    public static final String PRODUCT_INPUT_FILEPATH = "src\\main\\resources\\data\\origin\\product.origin.csv";
    public static final String PRODUCT_NEW_FILEPATH = "src\\main\\resources\\data\\new\\products.new.csv";
    public static final String PRODUCT_EDIT_FILEPATH = "src\\main\\resources\\data\\edit\\products.edit.csv";
    public static final String PRODUCT_DELETE_FILEPATH = "src\\main\\resources\\data\\delete\\products.delete.csv";

    public static final String CUSTOMER_OUTPUT_FILEPATH = "src\\main\\resources\\data\\output\\customer.output.csv";
    public static final String CUSTOMER_INPUT_FILEPATH = "src\\main\\resources\\data\\origin\\customer.origin.csv";
    public static final String CUSTOMER_NEW_FILEPATH = "src\\main\\resources\\data\\new\\customer.new.csv";
    public static final String CUSTOMER_EDIT_FILEPATH = "src\\main\\resources\\data\\edit\\customer.edit.csv";
    public static final String CUSTOMER_DELETE_FILEPATH = "src\\main\\resources\\data\\delete\\customer.delete.csv";

    public static final String ORDER_OUTPUT_FILEPATH = "src\\main\\resources\\data\\output\\order.output.csv";
    public static final String ORDER_INPUT_FILEPATH = "src\\main\\resources\\data\\origin\\order.origin.csv";
    public static final String ORDER_NEW_FILEPATH = "src\\main\\resources\\data\\new\\order.new.csv";
    public static final String ORDER_EDIT_FILEPATH = "src\\main\\resources\\data\\edit\\order.edit.csv";
    public static final String ORDER_DELETE_FILEPATH = "src\\main\\resources\\data\\delete\\order.delete.csv";

    protected DataLoader<K, T> dataLoader;
    protected DataWriter<T> dataWriter;
    protected ErrorLogger errorLogger;

    protected LinkedHashMap<K, T> dataMap;

    public BaseService(ModelParser<T> parser, ModelValidator<T> validator, ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
        this.dataLoader = new DataLoader<>(parser, validator, errorLogger);
        this.dataWriter = new DataWriter<>();
        dataMap = new LinkedHashMap<>();
    }

    public abstract LinkedHashMap<K, T> getData(String inputFilePath);

    public abstract void addDataList(String newDataFilePath);

    public abstract void editDataList(String editDataFilePath);

    public abstract void deleteDataList(String deleteDataFilePath);

    public abstract void writeData(String outputFilePath);
}
