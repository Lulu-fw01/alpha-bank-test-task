package com.lulu.dollargif.client;

import com.lulu.dollargif.dto.rate.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client of <a href="https://docs.openexchangerates.org/">docs.openexchangerates.org</a>
 * */
@FeignClient(name = "oxr-client", url = "${oxr.api.url}")
public interface OxrClient {

    /**
     * Get request.
     * */
    @GetMapping("/latest.json")
    ResponseEntity<Rate> getLatest(@RequestParam("app_id") String appId, @RequestParam("base") String base);

    /**
     * Get request.
     * */
    @GetMapping("/latest.json")
    ResponseEntity<Rate> getLatest(@RequestParam("app_id") String appId);

    /**
     * Get request.
     *
     * @param date String date in format yyyy-mm-dd.
     * @param appId your app id.
     * @param base change base currency (3-letter code, default: USD).
     * @apiNote different base values available only with Enterprise and Unlimited plans.
     * For more info follow <a href="https://docs.openexchangerates.org/docs/set-base-currency">link</a>.
     * */
    @GetMapping("/historical/{date}.json")
    ResponseEntity<Rate> getByDate(@PathVariable("date") String date, @RequestParam("app_id") String appId, @RequestParam("base") String base);

    /**
     * Get request.
     *
     * @param date String date in format yyyy-mm-dd.
     * @param appId your app id.
     * */
    @GetMapping("/historical/{date}.json")
    ResponseEntity<Rate> getByDate(@PathVariable("date") String date, @RequestParam("app_id") String appId);
}
