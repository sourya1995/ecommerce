package com.sourya.ecommerceAPI.exceptions;

public enum ErrorCode {
    GENERIC_ERROR("ERR-0001", "The system is unable to complete the request. Please contact system support."),
    HTTP_MEDIATYPE_NOT_SUPPORTED("ERR-0002", "Requested media type is not supported. Please use application/json or application/xml as 'Content-Type' header value."),
    HTTP_MESSAGE_NOT_WRITABLE("ERR-0003", "Missing 'Accept' header. Please add 'Accept' header."),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE("ERR-0004", "Requested 'Accept' header value is not supported. Please use application/json or application/xml as 'Accept' value."),
    JSON_PARSE_ERROR("ERR-0005", "Make sure request payload is a valid JSON object."),
    HTTP_MESSAGE_NOT_READABLE("ERR-0006", "Make sure request payload is a valid JSON/XML object according to 'Content-Type' ");

    private String errCode;
    private String errMsgKey;

    ErrorCode(final String errCode, final String errMsgKey){
        this.errCode = errCode;
        this.errMsgKey = errMsgKey;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }
}
