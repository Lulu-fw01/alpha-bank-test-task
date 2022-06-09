package com.lulu.dollargif.controller;

import com.lulu.dollargif.client.GifClient;
import com.lulu.dollargif.client.OxrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
@RequestMapping("/usd-gif")
public class DollarGifController {

    final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${giphy.api.key}")
    private String giphyApiKey;
    @Value("${oxr.api.id}")
    private String oxrId;

    private GifClient gifClient;
    private OxrClient oxrClient;

    private static String getYesterdayDate() {
        var date = LocalDate.now().minusDays(1);
        return date.format(dateFormat);
    }

    @Autowired
    public void setGifClient(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    @Autowired
    public void setOxrClient(OxrClient oxrClient) {
        this.oxrClient = oxrClient;
    }

    @GetMapping("/")
    String hello() {
        return "Hello world!";
    }

    @GetMapping("/get-gif/{currencyCode}")
    String getGif(@PathVariable("currencyCode") String currencyCode) {
        var todayRatesResp = oxrClient.getLatest(oxrId);
        var date = getYesterdayDate();
        var yesterdayRatesResp = oxrClient.getByDate(date, oxrId);

        var todayRate = Objects.requireNonNull(todayRatesResp.getBody()).getRates().get(currencyCode);
        var yesterdayRate = Objects.requireNonNull(yesterdayRatesResp.getBody()).getRates().get(currencyCode);

        var resp = gifClient.getGif(giphyApiKey, todayRate > yesterdayRate ? "rich" : "broke");
        return String.format("""
                <html>
                <header><title>Gif</title></header>
                <body>
                <b>yesterday: %s</b><p>
                <b>today: %s</b><p>
                <img src="%s"  width="250" /></body>
                </html>""", yesterdayRate, todayRate, Objects.requireNonNull(resp.getBody()).getData().getImages().getImage().getUrl());
    }
}
