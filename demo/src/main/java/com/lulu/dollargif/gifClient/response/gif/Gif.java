package com.lulu.dollargif.gifClient.response.gif;

import com.lulu.dollargif.gifClient.response.gif.images.Images;

public class Gif {
    private String url;
    private Images images;

    public Gif() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
