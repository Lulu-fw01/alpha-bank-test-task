package com.lulu.dollargif.client;

import com.lulu.dollargif.dto.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client of <a href="https://giphy.com/">giphy.com</a>
 * */
@FeignClient(name = "giphy-client", url = "${giphy.api.url}")
public interface GifClient {

    /**
     * Get request.
     * */
    @GetMapping(value = "/random")
    Gif getGif(@RequestParam("api_key") String api_key, @RequestParam("tag") String tag);
}
