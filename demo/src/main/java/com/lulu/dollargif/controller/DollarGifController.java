package com.lulu.dollargif.controller;

import com.lulu.dollargif.client.GifClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usd-gif")
public class DollarGifController {

    @Value("${giphy.api.key}")
    private String giphyApiKey;

    private GifClient gifClient;

    @Autowired
    public void setGifClient(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    @GetMapping("/")
    String hello() {
        return "Hello world!";
    }

    @GetMapping("/get-gif")
    String getGif() {
        var resp = gifClient.getGif(giphyApiKey, "rich");

        return String.format("""
                <html>
                <header><title>Gif</title></header>
                <body>
                <img src="%s"  width="250" /></body>
                </html>""", resp.getBody().getData().getImages().getImage().getUrl());
    }
}
