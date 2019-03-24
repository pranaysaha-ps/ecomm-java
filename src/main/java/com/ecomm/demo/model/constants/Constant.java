package com.ecomm.demo.model.constants;

import java.io.Serializable;

public final class Constant implements Serializable {

    private static final long serialVersionUID = -1L;

    public static final String COMPANY_NAME = "pranay-ecomm";
    public static final String WELCOME_MESSAGE = "Welcome api v1.0";
    public static final String AUTHENTICATED_USER = "authenticatedUser";
    public static final String STATUS = "status";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String ERROR = "errors";
    public static final String ID = "id";
    public static final String DATA = "data";
    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String USER_LIST_EMPTY = "User List is Empty!";
    public static final String INTERNAL_ERROR = "internal Server Error!";
    public static final String MESSAGE = "message";
    public static final String AUTHORISED_USER = "authorisedUser";
    public static final String AUTHORIZATION_FAILED = "Authorization Failed!";
    public static final String NOT_FOUND = "not found";

    //User related
    public static final String INVALID_CREDENTIAL = "Invalid Credential";

    // Product related
    public static final String LESS_PRODUCT_INVENTORY="Cannot process the order, order quantity is more than product quantity";
    public static final String NO_PRODUCT_INVENTORY="Cannot process the order, no product left";
    public static final String INTERNAL_SERVER_ERROR="Process failed due to internal server error";



}