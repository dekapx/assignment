package com.bloomberg.services.network.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NetworkServiceITest {
    @Autowired
    private NetworkService networkService;

    @Test
    public void multiply_withPositiveNumbers_returnMultiplication() {
        int result = networkService.multiply(2, 3);
        assertThat(result).isNotNegative().isEqualTo(6);
    }
}
