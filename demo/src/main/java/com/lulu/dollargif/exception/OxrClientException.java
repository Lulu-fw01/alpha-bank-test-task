package com.lulu.dollargif.exception;

public class OxrClientException extends Exception {
    private final int errorCode;
    private final String responseMessage;

    public OxrClientException(String message, int errorCode, String responseMessage) {
        super(message);
        this.errorCode = errorCode;
        this.responseMessage = responseMessage;
    }

    public OxrClientException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.responseMessage = "";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
