package com.management.validator;

import java.util.List;
import java.util.Set;

public interface ModelValidator<T> {
    public void validate(T model, List<Set<String>> existFields) throws Exception;
}
