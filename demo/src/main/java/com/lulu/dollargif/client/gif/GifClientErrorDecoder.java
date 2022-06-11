package com.lulu.dollargif.client.gif;

import feign.Response;
import feign.codec.ErrorDecoder;

public class GifClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400 -> {
                return new Exception("Bad Request Through Feign");
            }
            case 403 -> {
                return new Exception("Unauthorized Request Through Feign");
            }
            case 404 -> {
                return new Exception("Unidentified Request Through Feign");
            }
            case 414 -> {
                return new Exception();
            }
            case 429 -> {
                return new Exception();
            }
            default -> {
                return new Exception("Common Feign Exception");
            }
        }
    }
}
