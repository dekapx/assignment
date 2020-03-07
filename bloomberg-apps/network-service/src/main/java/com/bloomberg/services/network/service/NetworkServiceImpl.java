package com.bloomberg.services.network.service;

import com.bloomberg.services.network.model.MultiplyRequest;
import com.bloomberg.services.network.model.MultiplyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NetworkServiceImpl implements NetworkService {
    @Value("${calc.service.url}")
    private String calcServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public int multiply(final int firstArg, final int secondArg) {
        MultiplyRequest request = prepareMultiplyRequest(firstArg, secondArg);
        ResponseEntity<MultiplyResponse> result = restTemplate.postForEntity(calcServiceUrl, request, MultiplyResponse.class);
        MultiplyResponse response = result.getBody();
        return response.getResult();
    }

    private MultiplyRequest prepareMultiplyRequest(final int firstArg, final int secondArg) {
        return MultiplyRequest.builder()
                .firstArg(firstArg)
                .secondArg(secondArg)
                .build();
    }
}
