package com.lulu.dollargif.controller;

import com.lulu.dollargif.service.DollarGifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller with one GET http request.
 */
@RestController
@CrossOrigin(origins = "${cross.origin.client}")
@RequestMapping("/currency-gif")
public class DollarGifController {

    private final DollarGifService dollarGifService;

    @Autowired
    public DollarGifController(DollarGifService dollarGifService) {
        this.dollarGifService = dollarGifService;
    }

    /**
     * Request that return gif uri.
     * */
    @GetMapping("/gif/{currencyCode}")
    public ResponseEntity<?> getGif(@PathVariable("currencyCode") String currencyCode) {
        return dollarGifService.getGif(currencyCode);
    }
}
