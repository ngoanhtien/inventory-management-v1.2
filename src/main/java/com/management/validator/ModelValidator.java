package com.management.validator;

public interface ModelValidator<T> {
    public void validate(T model) throws Exception;
}
