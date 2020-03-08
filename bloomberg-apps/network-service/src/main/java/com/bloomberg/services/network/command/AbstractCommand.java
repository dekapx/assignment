package com.bloomberg.services.network.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

abstract class AbstractCommand {
    @Value("${calc.service.multiply.url}")
    protected String serviceUrl;

    @Autowired
    protected RestTemplate restTemplate;

    protected <R, T> R invokePostRequest(final String url, final T request, final Class<R> clazz) {
        final ResponseEntity<R> result = restTemplate.postForEntity(serviceUrl, request, clazz);
        return result.getBody();
    }
}
