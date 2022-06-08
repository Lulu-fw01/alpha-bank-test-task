package com.lulu.dollargif.gifClient.response;

import com.lulu.dollargif.gifClient.response.gif.Gif;

public class GifResponse {
    private Gif data;

    public GifResponse() {
    }

    public Gif getData() {
        return data;
    }

    public void setData(Gif data) {
        this.data = data;
    }
}
