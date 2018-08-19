package com.photoprint.network;

/**
 * Конфигурация API
 * 
 * @author Grigoriy Pryamov.
 */
public class ApiConfig {
    /**
     * Api URL
     */
    private final String url;

    public ApiConfig(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
