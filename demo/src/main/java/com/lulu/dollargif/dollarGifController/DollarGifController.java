package com.lulu.dollargif.dollarGifController;

import com.lulu.dollargif.gifClient.GifClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usd-gif")
public class DollarGifController {

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
        var resp = gifClient.getGif("SJaGwl4BR45fpnMauxr4OVPBPsnBnTq0", "rich");

        return String.format("""
                <html>
                <header><title>Gif</title></header>
                <body>
                <img src="%s"  width="250" /></body>
                </html>""", resp.getBody().getData().getImages().getImage().getUrl());
    }
}
