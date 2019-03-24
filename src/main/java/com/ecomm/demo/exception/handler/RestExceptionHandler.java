package com.ecomm.demo.exception.handler;

import com.ecomm.demo.exception.*;
import com.ecomm.demo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.CannotProceedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.BAD_REQUEST, error, ex);
    }


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String error = "Input validation error!";
        log.error(ex.getMessage(), ex);
        return buildResponseEntityWithData(HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getFieldError().getDefaultMessage(),error);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        String error = "Constraint violation error!";
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.BAD_REQUEST, error, ex);
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        String error = "Data Not Found!";
        log.error(ex.getMessage(), ex);
        return buildResponseEntityWithData(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.BAD_REQUEST, error, ex);
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String error = "Error writing JSON output";
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, error, ex);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);
        String error = "Data Integrity Violation!";
        return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, error, ex);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the InvalidDataAccessApiUsageException
     * @return the ApiError object
     */
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    protected ResponseEntity<Object> handleInvalidDataAccessApiUsage(InvalidDataAccessApiUsageException ex) {
        String error = "Invalid Data Access!";
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.EXPECTATION_FAILED, error, ex);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        String error = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.BAD_REQUEST, error, ex);
    }

    /**
     * Handle DataFoundNullException. Happens when request JSON is null.
     *
     * @param ex      DataFoundNullException
     * @return the ApiError object
     */
    @ExceptionHandler(DataFoundNullException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(DataFoundNullException ex) {
//        String error = "Request JSON found Null/Empty !";
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

    /**
     * Handle AuthorizationException. Happens when request JSON is null.
     *
     * @param ex      AuthorizationException
     * @return the ApiError object
     */
    @ExceptionHandler(AuthorizationException.class)
    protected ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex) {
        String error = "Unauthorized!";
        log.error(ex.getMessage(), ex);
        return buildResponseEntityWithData(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), error);
    }

    /**
     * Handle DuplicateKeyException. Happens when duplicate data found.
     *
     * @param ex      DuplicateKeyException
     * @return the ApiError object
     */
    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex) {
        String error = "Duplicate Key Found!";
        log.error(ex.getMessage(), ex);
        return buildResponseEntityWithData(HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage(), error);
    }

    /**
     * Handle InvalidInputExceptions. Happens when user not authenticated.
     *
     * @param ex InvalidInputExceptions
     * @return the ApiError object
     */
    @ExceptionHandler(InputValidationException.class)
    protected ResponseEntity<Object> handleInvalidInputServiceException(InputValidationException ex) {
        String error = "Invalid Input";
        log.error(ex.getMessage(), ex);
        return buildResponseEntityWithData(HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage(), error);
    }

    /**
     * Handle MultipartException. Happens when user uploads file of unsupported format.
     *
     * @param ex MultipartException
     * @return the ApiError object
     */
    @ExceptionHandler(MultipartException.class)
    protected ResponseEntity<Object> handleMultipartException(MultipartException ex) {
        String error = "Only png, jpg, jpeg files are supported";
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, error, ex);
    }

    /**
     * Handle CannotProceedException
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(CannotProceedException.class)
    public ResponseEntity<Object> handleCannotProceedException(CannotProceedException ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

    /**
     * Handle InvalidMfaAuthCodeException
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(InvalidMfaAuthCodeException.class)
    public ResponseEntity<Object> handleInvalidMfaAuthCodeException(InvalidMfaAuthCodeException ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        String error = "Service Unavailable!";
        return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, error, ex);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String errorMessage, Exception ex) {
        return new ResponseEntity<>(ResponseUtil.getFailedResponseJson(ex.getLocalizedMessage(), errorMessage), httpStatus);
    }
    private ResponseEntity<Object> buildResponseEntityWithData(HttpStatus httpStatus, String errorMessage, Object data) {
        return new ResponseEntity<>(ResponseUtil.getFailedResponseJsonWithData( data, errorMessage), httpStatus);
    }

}
