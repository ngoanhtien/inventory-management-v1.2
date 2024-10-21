package com.management.parser;

public interface ModelParser<T> {
    T parse(String line) throws Exception;
}
