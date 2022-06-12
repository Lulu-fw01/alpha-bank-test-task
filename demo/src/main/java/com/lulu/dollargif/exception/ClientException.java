package com.lulu.dollargif.exception;

public class ClientException extends Exception {
    private final int errorCode;
    private final String responseMessage;

    public ClientException(String message, int errorCode, String responseMessage) {
        super(message);
        this.errorCode = errorCode;
        this.responseMessage = responseMessage;
    }

    public ClientException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.responseMessage = "";
    }

    public ClientException(String message) {
        super(message);
        this.errorCode = 400;
        this.responseMessage = "";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
