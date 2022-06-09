package com.lulu.dollargif.dto.gif;

import com.lulu.dollargif.dto.gif.images.Images;

public class GifData {
    private String url;
    private Images images;

    public GifData() {
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
