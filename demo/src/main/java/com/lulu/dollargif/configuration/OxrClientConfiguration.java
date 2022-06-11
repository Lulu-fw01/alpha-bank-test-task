package com.lulu.dollargif.configuration;

import com.lulu.dollargif.client.oxr.OxrClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OxrClientConfiguration {
    @Bean
    public ErrorDecoder oxrClientErrorDecoder() {
        return new OxrClientErrorDecoder();
    }
}
