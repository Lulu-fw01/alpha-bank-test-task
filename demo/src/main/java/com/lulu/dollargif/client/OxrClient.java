package com.lulu.dollargif.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "oxr-client", url = "${oxr.api.url}")
public interface OxrClient {
}
