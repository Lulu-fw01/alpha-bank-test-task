package com.lulu.dollargif.dto.rate;

import java.util.Map;

public class Rate {
    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, Double> rates;

    public String getDisclaimer() { return disclaimer; }
    public void setDisclaimer(String value) { this.disclaimer = value; }

    public String getLicense() { return license; }
    public void setLicense(String value) { this.license = value; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long value) { this.timestamp = value; }

    public String getBase() { return base; }
    public void setBase(String value) { this.base = value; }

    public Map<String, Double> getRates() { return rates; }
    public void setRates(Map<String, Double> value) { this.rates = value; }
}
