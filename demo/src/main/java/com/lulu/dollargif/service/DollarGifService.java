package com.lulu.dollargif.service;

import com.lulu.dollargif.client.gif.GifClient;
import com.lulu.dollargif.client.oxr.OxrClient;
import com.lulu.dollargif.dto.Gif;
import com.lulu.dollargif.dto.rate.Rate;
import com.lulu.dollargif.exception.ClientException;
import com.lulu.dollargif.model.GifRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Service for
 *
 * */
@Service
public class DollarGifService {

    final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${giphy.api.key}")
    private String giphyApiKey;
    @Value("${oxr.api.id}")
    private String oxrId;

    private GifClient gifClient;
    private OxrClient oxrClient;

    @Autowired
    public void setGifClient(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    @Autowired
    public void setOxrClient(OxrClient oxrClient) {
        this.oxrClient = oxrClient;
    }

    private static String getYesterdayDate() {
        var date = LocalDate.now().minusDays(1);
        return date.format(dateFormat);
    }

    public ResponseEntity<?> getGif(String currencyCode) {
        Rate todayRatesResp;
        try {
            todayRatesResp = oxrClient.getLatest(oxrId);
        } catch (ClientException e) {
            return ResponseEntity.status(e.getErrorCode()).body("Oxr feign client error.");
        }

        var date = getYesterdayDate();
        Rate yesterdayRatesResp;
        try {
            yesterdayRatesResp = oxrClient.getByDate(date, oxrId);
        } catch (ClientException e) {
            return ResponseEntity.status(e.getErrorCode()).body("Oxr feign client error.");
        }

        if (!todayRatesResp.getRates().containsKey(currencyCode)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such currency code.");
        }

        var todayRate = todayRatesResp.getRates().get(currencyCode);
        var yesterdayRate = yesterdayRatesResp.getRates().get(currencyCode);
        Gif gif;
        try {
            gif = gifClient.getGif(giphyApiKey, todayRate > yesterdayRate ? "rich" : "broke");
        } catch (ClientException e) {
            return ResponseEntity.status(e.getErrorCode()).body("Gif client error.");
        }

        var gifRate = new GifRate();
        gifRate.url = gif.getData().getImages().getImage().getUrl();
        gifRate.yesterdayRate = yesterdayRate;
        gifRate.todayRate = todayRate;

        return ResponseEntity.ok(gifRate);
    }

}
