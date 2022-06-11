package com.lulu.dollargif.client.gif;

import com.lulu.dollargif.configuration.GifClientConfiguration;
import com.lulu.dollargif.dto.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client of <a href="https://giphy.com/">giphy.com</a>
 */
@FeignClient(name = "giphy-client", url = "${giphy.api.url}", configuration = GifClientConfiguration.class)
public interface GifClient {

    /**
     * Get request of random gif.
     *
     * @param api_key of giphy.com.
     * @param tag     tag of request. It is something like theme of the gif.
     *
     * @return dto {@link Gif}.
     */
    @GetMapping(value = "/random")
    Gif getGif(@RequestParam("api_key") String api_key, @RequestParam("tag") String tag);
}
