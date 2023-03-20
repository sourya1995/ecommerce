package com.sourya.ecommerceAPI.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public ResourceNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }
}
