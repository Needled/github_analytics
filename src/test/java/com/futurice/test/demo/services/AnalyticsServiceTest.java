package com.futurice.test.demo.services;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class AnalyticsServiceTest {

    @InjectMocks
    private AnalyticsService analyticsService;

    @Mock
    private TextAnalyticsClient textAnalyticsClient;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getTextSentimentClassLongMap_emptyList_shouldReturnEmptyMap() {
        List<String> comments = new ArrayList<>();
        Map<TextSentimentClass, Long> output = analyticsService.getTextSentimentClassLongMap(comments);
        assertThat(output, equalTo(new HashMap<>()));
    }

    @Test
    public void getTextSentimentClassLongMap_validList_shouldReturnSentimentMap() {
        // given
        List<String> comments = new ArrayList<>();
        comments.addAll(Arrays.asList("a"));
        AnalyzeSentimentResult sentimentResult =  new AnalyzeSentimentResult(
                "1",
                new TextDocumentStatistics(5, 10),
                null,
                new TextSentiment(TextSentimentClass.NEUTRAL,0.02, 0.9, 0.08,5,2),
                null);
        DocumentResultCollection<AnalyzeSentimentResult> collectionResult = new DocumentResultCollection<AnalyzeSentimentResult>(Collections.singleton(sentimentResult),"1.0.0",new TextDocumentBatchStatistics(1,1,0,5L));
        given(textAnalyticsClient.analyzeSentiment(comments)).willReturn(collectionResult);
        Map<TextSentimentClass,Long> expectedOutput = new HashMap<>();
        expectedOutput.put(TextSentimentClass.NEUTRAL, 1L);

        // when
        Map<TextSentimentClass, Long> output = analyticsService.getTextSentimentClassLongMap(comments);

        // then
        assertThat(output, equalTo(expectedOutput));
    }
}
