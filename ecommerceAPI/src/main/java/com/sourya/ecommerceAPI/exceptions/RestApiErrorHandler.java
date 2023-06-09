package com.sourya.ecommerceAPI.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Locale;

@ControllerAdvice
public class RestApiErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiErrorHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public RestApiErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex, Locale locale){
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.GENERIC_ERROR.getErrMsgKey(), ErrorCode.GENERIC_ERROR.getErrCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("HttpMediaTypeNotSupportedException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex, Locale locale){
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrMsgKey(), ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrCode(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("HttpMediaTypeNotSupportedException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<Error> handleHttpMessageNotWritableException(HttpServletRequest request, HttpMessageNotWritableException ex, Locale locale) {
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrMsgKey(), ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrCode(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("HttpMessageNotWritableException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotAcceptableException(HttpServletRequest request, HttpMediaTypeNotAcceptableException ex, Locale locale){
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrMsgKey(), ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrCode(),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("HttpMediaTypeNotAcceptableException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex, Locale locale){
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrMsgKey(), ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrCode(),
               HttpStatus.NOT_ACCEPTABLE.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("HttpMessageNotReadableException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Error> handleJsonParseException(HttpServletRequest request, JsonParseException ex, Locale locale) {
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.JSON_PARSE_ERROR.getErrMsgKey(), ErrorCode.JSON_PARSE_ERROR.getErrCode(),
                HttpStatus.NOT_ACCEPTABLE.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("JsonParseException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex, Locale locale) {
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.RESOURCE_NOT_FOUND.getErrMsgKey(), ErrorCode.RESOURCE_NOT_FOUND.getErrCode(),
                HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("ResourceNotFoundException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Error> handleCustomerNotFoundException(HttpServletRequest request, CustomerNotFoundException ex, Locale locale) {
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.CUSTOMER_NOT_FOUND.getErrMsgKey(), ErrorCode.CUSTOMER_NOT_FOUND.getErrCode(),
                HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("CustomerNotFoundException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Error> handleItemNotFoundException(HttpServletRequest request, ItemNotFoundException ex, Locale locale) {
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.ITEM_NOT_FOUND.getErrMsgKey(), ErrorCode.ITEM_NOT_FOUND.getErrCode(),
                HttpStatus.NOT_FOUND.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        LOGGER.info("ItemNotFoundException :: request.getMethod()" + request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(GenericAlreadyExistsException.class)
    public ResponseEntity<Error> handleGenericAlreadyExistsException(HttpServletRequest request, GenericAlreadyExistsException ex, Locale locale) {
        ex.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.GENERIC_ALREADY_EXISTS.getErrMsgKey(), ErrorCode.GENERIC_ALREADY_EXISTS.getErrCode(),
                HttpStatus.NOT_ACCEPTABLE.value()).setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTimestamp(Instant.now());
        LOGGER.info("GenericAlreadyExistsException :: request.getMethod()" + request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
}
