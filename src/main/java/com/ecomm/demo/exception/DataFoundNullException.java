package com.ecomm.demo.exception;

import org.springframework.util.StringUtils;

public class DataFoundNullException extends Exception {

    public DataFoundNullException(String clazz) {
        super(DataFoundNullException.generateMessage(clazz));
    }

    private static String generateMessage(String entity) {
        return StringUtils.capitalize(entity)
                + " not found";
    }

}
