package com.elif.mlops;

public class LinkedWorkspaceNotFoundException extends RuntimeException {
    public LinkedWorkspaceNotFoundException(String message) {
        super(message);
    }
}