package com.management.service;

import com.management.dal.DataLoader;
import com.management.dal.DataWriter;
import com.management.model.CSVConvertible;
import com.management.parser.ModelParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

public abstract class BaseService<T extends CSVConvertible> {
    protected DataLoader<T> dataLoader;
    protected DataWriter<T> dataWriter;
    protected ErrorLogger errorLogger;

    public BaseService(ModelParser<T> parser, ModelValidator<T> validator, ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
        this.dataLoader = new DataLoader<>(parser, validator, errorLogger);
        this.dataWriter = new DataWriter<>();
    }

    public abstract void getData(String inputFilePath);

    public abstract void addDataList(String newDataFilePath);

    public abstract void writeData(String outputFilePath);
}
