package com.lulu.dollargif.client.oxr;

import com.lulu.dollargif.exception.ClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class OxrClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        var reason = response.reason();
        switch (response.status()) {
            case 400 -> {
                return new ClientException("Unsupported base currency.", 400);
            }
            case 401 -> {
                return new ClientException("Server error, problems with app_id.", 401);
            }
            case 403 -> {
                return new ClientException("Too much requests.", 403);
            }
            case 404 -> {
                return new ClientException("Resource no found.", 404);
            }
            case 429 -> {
                return new ClientException("No permission.", 429);
            }
            default -> {
                return new ClientException("Common Feign Exception");
            }
        }
    }
}
