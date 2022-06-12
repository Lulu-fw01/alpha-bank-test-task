package com.lulu.dollargif;

import com.lulu.dollargif.client.gif.GifClient;
import com.lulu.dollargif.client.oxr.OxrClient;
import com.lulu.dollargif.controller.DollarGifController;
import com.lulu.dollargif.dto.Gif;
import com.lulu.dollargif.dto.gif.GifData;
import com.lulu.dollargif.dto.gif.images.Images;
import com.lulu.dollargif.dto.gif.images.image.Image;
import com.lulu.dollargif.dto.rate.Rate;
import com.lulu.dollargif.model.GifRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DollarGifApplication.class)
@WebMvcTest(DollarGifController.class)
class DollarGifApplicationTests {

    @MockBean
    private GifClient gifClient;

    @MockBean
    private OxrClient oxrClient;

    @Autowired
    private DollarGifController dgController;

    @Test
    void contextLoads() {
    }

    private Gif richGif() {
        var gif = new Gif();
        var data = new GifData();
        var images = new Images();
        var image = new Image();
        image.setUrl("rich gif");
        images.setImage(image);
        data.setImages(images);
        gif.setData(data);
        return gif;
    }

    private Gif brokeGif() {
        var gif = new Gif();
        var data = new GifData();
        var images = new Images();
        var image = new Image();
        image.setUrl("broke gif");
        images.setImage(image);
        data.setImages(images);
        gif.setData(data);
        return gif;
    }

    @Test
    void getGifTest() {
        var yesterdayRate = new Rate();
        yesterdayRate.setBase("USD");
        var yMap = new HashMap<String, Double>();
        yMap.put("USD", 1.0);
        yMap.put("EUR", 3.67);
        yMap.put("RUB", 58.9);
        yesterdayRate.setRates(yMap);
        Mockito.when(oxrClient.getByDate(any(), any())).thenReturn(yesterdayRate);

        var todayRate = new Rate();
        todayRate.setBase("USD");
        var tMap = new HashMap<String, Double>();
        tMap.put("USD", 1.0);
        tMap.put("EUR", 3.99);
        tMap.put("RUB", 57.9);
        todayRate.setRates(tMap);
        Mockito.when(oxrClient.getLatest(any())).thenReturn(todayRate);

        var rGif = richGif();
        Mockito.when(gifClient.getGif(any(), eq("rich"))).thenReturn(rGif);
        var resp = dgController.getGif("EUR");
        GifRate model = (GifRate) resp.getBody();
        assertThat(model.todayRate).isEqualTo(3.99);
        assertThat(model.yesterdayRate).isEqualTo(3.67);
        assertThat(model.url).isEqualTo("rich gif");

        var bGif = brokeGif();
        Mockito.when(gifClient.getGif(any(), eq("broke"))).thenReturn(bGif);
        resp = dgController.getGif("RUB");
        model = (GifRate) resp.getBody();
        assertThat(model.todayRate).isEqualTo(57.9);
        assertThat(model.yesterdayRate).isEqualTo(58.9);
        assertThat(model.url).isEqualTo("broke gif");

        resp = dgController.getGif("RU");
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
