package com.futurice.test.demo.services;

import com.futurice.test.demo.domain.Comment;
import com.futurice.test.demo.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class GithubAccessServiceTest {

    private static final String GITHUB_ACCESS_TOKEN = null;
    private static final String GITHUB_API_URL = null;

    @InjectMocks
    private GithubAccessService githubAccessService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getComments_repositoryNotFound_shouldThrowNotFoundException() {
        // given
        String user = "test_user";
        String repository = "test_repo";
        String url = GITHUB_API_URL + user + "/" + repository + "/issues/comments";
        HttpEntity<String> requestEntity = new HttpEntity<>(null, getMockHttpHeaders());
        ResponseEntity<Comment[]> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        given(restTemplate.exchange(url, HttpMethod.GET,requestEntity,Comment[].class)).willThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class, () -> {
            Comment[] output = githubAccessService.getComments(user, repository);
        });
    }

    @Test
    public void getComments_repositoryAndUserValid_shouldReturnComments() {
        // given
        String user = "test_user";
        String repository = "test_repo";
        String url = GITHUB_API_URL + user + "/" + repository + "/issues/comments";
        HttpEntity<String> requestEntity = new HttpEntity<>(null, getMockHttpHeaders());
        Comment comment = new Comment().setBody("test comment");
        Comment[] expectedOutput = {comment};
        ResponseEntity<Comment[]> responseEntity = new ResponseEntity<>(expectedOutput, HttpStatus.OK);
        given(restTemplate.exchange(url, HttpMethod.GET,requestEntity,Comment[].class)).willReturn(responseEntity);

        Comment[] serviceOutput = githubAccessService.getComments(user,repository);
        assertThat(serviceOutput, equalTo(expectedOutput));
    }

    private HttpHeaders getMockHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "token " + GITHUB_ACCESS_TOKEN);
        return httpHeaders;
    }

}
