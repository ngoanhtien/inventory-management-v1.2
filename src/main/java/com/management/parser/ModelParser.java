package com.management.parser;

public interface ModelParser<T> {
    String SPLIT_CHARACTER = ",";
    T parse(String line) throws Exception;
}
