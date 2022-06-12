package com.lulu.dollargif.controller;

import com.lulu.dollargif.client.gif.GifClient;
import com.lulu.dollargif.client.oxr.OxrClient;
import com.lulu.dollargif.model.GifRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "${cross.origin.client}")
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

    @GetMapping("/gif/{currencyCode}")
    public ResponseEntity<?> getGif(@PathVariable("currencyCode") String currencyCode) {
        var todayRatesResp = oxrClient.getLatest(oxrId);

        var date = getYesterdayDate();
        var yesterdayRatesResp = oxrClient.getByDate(date, oxrId);

        if (!todayRatesResp.getRates().containsKey(currencyCode)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such currency code.");
        }
        var todayRate = todayRatesResp.getRates().get(currencyCode);
        var yesterdayRate = yesterdayRatesResp.getRates().get(currencyCode);

        var gif = gifClient.getGif(giphyApiKey, todayRate > yesterdayRate ? "rich" : "broke");

        var gifRate = new GifRate();
        gifRate.url = gif.getData().getImages().getImage().getUrl();
        gifRate.yesterdayRate = yesterdayRate;
        gifRate.todayRate = todayRate;

        return ResponseEntity.ok(gifRate);
    }
}
