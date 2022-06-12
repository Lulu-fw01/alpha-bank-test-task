package com.lulu.dollargif.client.gif;

import com.lulu.dollargif.exception.ClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class GifClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400 -> {
                return new ClientException("Something incorrect in request, maybe missed required parameters.", 400);
            }
            case 403 -> {
                return new ClientException("Problem with api key.", 403);
            }
            case 404 -> {
                return new ClientException("Unidentified Request Through Feign.", 404);
            }
            case 414 -> {
                return new ClientException("Url too long.", 414);
            }
            case 429 -> {
                return new ClientException("Too many requests.", 429);
            }
            default -> {
                return new ClientException("Common Feign Exception");
            }
        }
    }
}
