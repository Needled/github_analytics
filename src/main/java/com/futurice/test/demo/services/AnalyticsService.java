package com.futurice.test.demo.services;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.models.AnalyzeSentimentResult;
import com.azure.ai.textanalytics.models.DocumentResultCollection;
import com.azure.ai.textanalytics.models.TextSentimentClass;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private TextAnalyticsClient textAnalyticsClient;

    public AnalyticsService(TextAnalyticsClient textAnalyticsClient) {
        this.textAnalyticsClient = textAnalyticsClient;
    }

    public Map<TextSentimentClass, Long> getTextSentimentClassLongMap(List<String> stringComments) {
        if(stringComments == null || stringComments.isEmpty()){
            return new HashMap<>();
        }
        DocumentResultCollection<AnalyzeSentimentResult> analysisSentimentResult =  textAnalyticsClient.analyzeSentiment(stringComments);
        return analysisSentimentResult.stream().map(document -> document.getDocumentSentiment().getTextSentimentClass()).collect( Collectors.groupingBy( Function.identity(), Collectors.counting()));
    }
}
