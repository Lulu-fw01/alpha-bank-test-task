package com.lulu.dollargif.controller;

import com.lulu.dollargif.client.GifClient;
import com.lulu.dollargif.client.OxrClient;
import com.lulu.dollargif.model.GifRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currency-gif")
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

    @Deprecated
    private static String getGifHtml(Double yesterdayRate, Double todayRate, String gifUrl) {
        return String.format("""
                <html>
                <header><title>Gif</title></header>
                <body>
                <b>yesterday: %s</b><p>
                <b>today: %s</b><p>
                <img src="%s"  width="250" /></body>
                </html>""", yesterdayRate, todayRate, gifUrl);
    }

    @GetMapping("/gif/{currencyCode}")
    GifRate getGif(@PathVariable("currencyCode") String currencyCode) {
        var todayRatesResp = oxrClient.getLatest(oxrId);

        var date = getYesterdayDate();
        var yesterdayRatesResp = oxrClient.getByDate(date, oxrId);

        var todayRate = todayRatesResp.getRates().get(currencyCode);
        var yesterdayRate = yesterdayRatesResp.getRates().get(currencyCode);

        var gif = gifClient.getGif(giphyApiKey, todayRate > yesterdayRate ? "rich" : "broke");

        var gifRate = new GifRate();
        gifRate.url = gif.getData().getImages().getImage().getUrl();
        gifRate.yesterdayRate = yesterdayRate;
        gifRate.todayRate = todayRate;
        return gifRate;
    }
}
