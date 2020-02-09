package com.futurice.test.demo.domain;

import com.azure.ai.textanalytics.models.TextSentimentClass;

import java.util.Map;

public class SentimentResults {
    private long positive = 0L;
    private long mixed = 0L;
    private long negative = 0L;
    private long neutral = 0L;

    public SentimentResults(Map<TextSentimentClass, Long> sentimentMap) {
        positive = sentimentMap.getOrDefault(TextSentimentClass.POSITIVE, 0L);
        mixed = sentimentMap.getOrDefault(TextSentimentClass.MIXED, 0L);
        negative = sentimentMap.getOrDefault(TextSentimentClass.NEGATIVE, 0L);
        neutral = sentimentMap.getOrDefault(TextSentimentClass.NEUTRAL, 0L);
    }

    public long getPositive() {
        return positive;
    }

    public void setPositive(Long positive) {
        this.positive = positive;
    }

    public long getMixed() {
        return mixed;
    }

    public void setMixed(long mixed) {
        this.mixed = mixed;
    }

    public long getNegative() {
        return negative;
    }

    public void setNegative(long negative) {
        this.negative = negative;
    }

    public long getNeutral() {
        return neutral;
    }

    public void setNeutral(long neutral) {
        this.neutral = neutral;
    }
}
