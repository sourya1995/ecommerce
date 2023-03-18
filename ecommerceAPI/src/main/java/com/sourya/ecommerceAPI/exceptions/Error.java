package com.sourya.ecommerceAPI.exceptions;


import org.apache.logging.log4j.util.Strings;

public class Error {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String message;
    private Integer status;
    private String url = "not available";

    private String reqMethod = "Not available";

    public String getReqMethod() {
        return reqMethod;
    }

    public Error setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
        return null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public Error setUrl(String url) {
        if(Strings.isNotBlank(url)){
            this.url = url;
        }
       return this;
    }
}
