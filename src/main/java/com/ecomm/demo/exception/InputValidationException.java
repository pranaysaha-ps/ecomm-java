package com.ecomm.demo.exception;


public class InputValidationException extends Exception {

    public InputValidationException(String clazz) {
        super(InputValidationException.generateMessage(clazz));
    }

    private static String generateMessage(String entity) {
        return entity;
    }

}
