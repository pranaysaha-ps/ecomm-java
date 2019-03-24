package com.ecomm.demo.exception;

import org.springframework.util.StringUtils;

public class InvalidMfaAuthCodeException extends Exception {

    public InvalidMfaAuthCodeException(String clazz) {
        super(InvalidMfaAuthCodeException.generateMessage(clazz));
    }

    private static String generateMessage(String entity) {
        return StringUtils.capitalize(entity)
                + " for authentication";
    }
}
