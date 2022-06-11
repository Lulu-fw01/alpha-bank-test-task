package com.lulu.dollargif.configuration;

import com.lulu.dollargif.client.gif.GifClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GifClientConfiguration {

    @Bean
    public ErrorDecoder gifClientErrorDecoder() {
        return new GifClientErrorDecoder();
    }
}
