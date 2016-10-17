package com.zzqfsy.cloud.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by john on 16-10-17.
 */
@Component
@ConfigurationProperties(prefix = "servers.jpushCenter")
public class JpushCenterProperties {
    private String host;
    private String title;
    private String destination;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}