package com.futurice.test.demo.config;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().errorHandler(new ResponseErrorHandler()).build();
    }

    @Bean
    public TextAnalyticsClient textAnalyticsClient(@Value("${azure.endpoint}") String azureEndpoint, @Value("${azure.subscriptionKey}") String azureKey) {
        return new TextAnalyticsClientBuilder()
                .subscriptionKey(azureKey)
                .endpoint(azureEndpoint)
                .buildClient();
    }
}
