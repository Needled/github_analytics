package com.futurice.test.demo.services;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.AnalyzeSentimentResult;
import com.azure.ai.textanalytics.models.DocumentResultCollection;
import com.azure.ai.textanalytics.models.TextSentimentClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private static TextAnalyticsClient analyticsClient;

    public AnalyticsService(@Value("${azure.subscriptionKey}") String azureKey,
                            @Value("${azure.endpoint}") String azureEndpoint) {
        analyticsClient = new TextAnalyticsClientBuilder()
                .subscriptionKey(azureKey)
                .endpoint(azureEndpoint)
                .buildClient();
    }

    public Map<TextSentimentClass, Long> getTextSentimentClassLongMap(List<String> stringComments) {
        DocumentResultCollection<AnalyzeSentimentResult> anal =  analyticsClient.analyzeSentiment(stringComments);
        return anal.stream().map(document -> document.getDocumentSentiment().getTextSentimentClass()).collect( Collectors.groupingBy( Function.identity(), Collectors.counting()));
    }
}
