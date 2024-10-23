package com.management.validator;

import java.util.Map;
import java.util.Set;

public interface ModelValidator<T> {
    public void validate(T model) throws Exception;
    void setExistingFields(Map<String, Set<String>> existingFields);
}
