package com.lulu.dollargif.gifClient;

import com.lulu.dollargif.dollarGifController.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif-client", url = "https://api.giphy.com/v1/gifs")
public interface GifClient {

    @GetMapping(value = "/random")
    ResponseEntity<GifResponse> getRichGif(@RequestParam("api_key") String api_key, @RequestParam("tag") String tag);
}
