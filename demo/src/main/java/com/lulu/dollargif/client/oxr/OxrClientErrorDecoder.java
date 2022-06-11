package com.lulu.dollargif.client.oxr;

import com.lulu.dollargif.exception.OxrClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class OxrClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        var reason = response.reason();
        switch (response.status()) {
            case 400 -> {
                return new OxrClientException("Unsupported base currency.", 400);
            }
            case 401 -> {
                return new OxrClientException("Server error, problems with app_id.", 401);
            }
            case 403 -> {
                return new OxrClientException("Too much requests.", 403);
            }
            case 404 -> {
                return new OxrClientException("Resource no found.", 404);
            }
            case 429 -> {
                return new OxrClientException("No permission.", 429);
            }
            default -> {
                return new Exception("Common Feign Exception");
            }
        }
    }
}
