package com.management.service;

import com.management.model.Order;
import com.management.parser.ModelParser;
import com.management.utils.ErrorLogger;
import com.management.validator.ModelValidator;

public class OrderService extends BaseService<Order> {

    public OrderService(ModelParser<Order> parser, ModelValidator<Order> validator, ErrorLogger errorLogger) {
        super(parser, validator, errorLogger);
    }

    @Override
    public void getData(String inputFilePath) {

    }

    @Override
    public void addDataList(String newDataFilePath) {

    }

    @Override
    public void writeData(String outputFilePath) {

    }
}
