package com.lulu.dollargif.dto.gif.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lulu.dollargif.dto.gif.images.image.Image;

public class Images {
    private Image image;

    public Images() {
    }

    public Image getImage() {
        return image;
    }

    @JsonProperty("preview_gif")
    public void setImage(Image image) {
        this.image = image;
    }
}
