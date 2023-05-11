package com.rosser.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 需要在上级应用给出的配置
 */
@Component
@Data
@ConfigurationProperties(prefix = "rosser")
public class Properties {
    private String apikey;
    private String chatApiUrl;
    private String proxyHost;
    private Integer proxyPort;
}
