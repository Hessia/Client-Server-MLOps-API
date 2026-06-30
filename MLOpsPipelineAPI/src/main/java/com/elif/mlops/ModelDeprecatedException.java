package com.elif.mlops;

public class ModelDeprecatedException extends RuntimeException {
    public ModelDeprecatedException(String message) {
        super(message);
    }
}