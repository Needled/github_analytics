package com.futurice.test.demo.config;

import com.futurice.test.demo.exceptions.NotFoundException;
import com.futurice.test.demo.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@ControllerAdvice
public class ResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NotFoundException("Resource requested was not found");
        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("This operation was unauthorized. Please authenticate");
        } else {
            throw new HttpClientErrorException(response.getStatusCode());
        }
    }
}
