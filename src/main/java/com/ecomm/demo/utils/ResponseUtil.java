package com.ecomm.demo.utils;

import com.ecomm.demo.model.constants.Constant;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ResponseUtil {

    public static Map<String, String> getSuccessResponseJson(String message) {
        Map<String, String> responseBuilder = new HashMap<>();
        setResponse(Constant.SUCCESS,null, message, responseBuilder);
        return responseBuilder;
    }

    public static Map<String, Object> getSuccessResponseJsonWithData(Object data, String message) {
        Map<String, Object> responseBuilder = new HashMap<>();
        setResponse(Constant.SUCCESS, data, message, responseBuilder);
        return responseBuilder;
    }

    public static Map<String, Object> getFailedResponseJson(String localizedMessage, String errorMessage) {
        Map<String, Object> responseBuilder = new HashMap<>();
        responseBuilder.put(Constant.ERROR, errorMessage);
        setResponse(Constant.FAILURE, localizedMessage, errorMessage, responseBuilder);
        return responseBuilder;
    }

    public static Map<String, Object> getFailedResponseJsonWithData(Object data, String errorMessage) {
        Map<String, Object> responseBuilder = new HashMap<>();
        setResponse(Constant.FAILURE, data, errorMessage, responseBuilder);
        return responseBuilder;
    }

    private static void setResponse(String status, Object data, String message, Map responseBuilder){
        responseBuilder.put(Constant.STATUS, status);
        responseBuilder.put(Constant.DATA, data);
        responseBuilder.put(Constant.MESSAGE, message);
    }
}
