package com.futurice.test.demo.controllers;

import com.azure.ai.textanalytics.models.AnalyzeSentimentResult;
import com.azure.ai.textanalytics.models.DocumentResultCollection;
import com.azure.ai.textanalytics.models.TextSentimentClass;
import com.futurice.test.demo.domain.Comment;
import com.futurice.test.demo.domain.SentimentResults;
import com.futurice.test.demo.services.AnalyticsService;
import com.futurice.test.demo.services.GithubAccessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Api(value = "Github analytics service", produces = "application/json")
public class AnalyticsController {

    private static final Logger log = LoggerFactory.getLogger(AnalyticsController.class);

    private final GithubAccessService githubAccessService;
    private final AnalyticsService analyticsService;

    public AnalyticsController(GithubAccessService githubAccessService,
                               AnalyticsService analyticsService) {
        this.githubAccessService = githubAccessService;
        this.analyticsService = analyticsService;
    }

    @ApiOperation("Get stats")
    @GetMapping("/users/{user}/repositories/{repository}/stats")
    public SentimentResults getRepositoryAnalysis(@PathVariable String user, @PathVariable String repository) {

        Comment[] comments = githubAccessService.getComments(user, repository);

        List<String> stringComments = Arrays.stream(comments).map(comment -> comment.getBody()).collect(Collectors.toList());
//        log.info("comments: {}", stringComments);

        Map<TextSentimentClass, Long> textSentiments = analyticsService.getTextSentimentClassLongMap(stringComments);

        log.info("text sentiments: {}", textSentiments);

        return new SentimentResults(textSentiments);
    }
}
