package com.zzqfsy.cloud.config.client.controller;

import com.zzqfsy.cloud.config.client.properties.JpushCenterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with j360-cloud-all -> me.j360.cloud.client.
 * User: min_xu
 * Date: 2015/10/7
 * Time: 22:37
 * 说明：
 */

@RestController
@RefreshScope
public class MessageController {

    @Value("${apply.message:local message}")
    public String message;

    @Autowired
    private JpushCenterProperties jpushCenterProperties;


    @RequestMapping("/")
    public String home() {
        return message;
    }

    @RequestMapping("/title")
    public String title() {
        return jpushCenterProperties.getTitle();
    }
}
