package com.futurice.test.demo.services;

import com.futurice.test.demo.domain.Comment;
import com.futurice.test.demo.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubAccessService {

    private static final Logger log = LoggerFactory.getLogger(GithubAccessService.class);

    @Value("${github.api.url}")
    private String GITHUB_API_URL;

    @Value("${github.access.token}")
    private String GITHUB_ACCESS_TOKEN;

    private final RestTemplate restTemplate;

    public GithubAccessService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Comment[] getComments(String user, String repository) {
        String url = GITHUB_API_URL + user + "/" + repository + "/issues/comments";
        log.info("URL: {}", url);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, getHttpHeaders());
        ResponseEntity<Comment[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Comment[].class); //todo pagination needed - only analyze first page now
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        else if(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND){
            throw new NotFoundException("Repository requested was not found");
        }

        throw new HttpClientErrorException(responseEntity.getStatusCode());
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "token " + GITHUB_ACCESS_TOKEN);
        return httpHeaders;
    }
}
