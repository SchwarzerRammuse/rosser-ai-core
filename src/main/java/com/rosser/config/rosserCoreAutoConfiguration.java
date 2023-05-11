package com.rosser.config;

import com.rosser.api.RosserAI;
import com.rosser.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * stater核心类
 */
@Configuration
@EnableConfigurationProperties(Properties.class)
public class rosserCoreAutoConfiguration {
    @Autowired
    private Properties properties;
    @Bean
    public OpenAIService openAIService(Properties properties){
        return new OpenAIService(properties);
    }
    @Bean
    public RosserAI rosserAI(OpenAIService openAIService){
        return new RosserAI(properties,openAIService);
    }

}