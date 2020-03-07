package com.bloomberg.services.network.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

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

    @Test
    public void multiply_withParallelRequests_returnMultiplications() throws Exception {
        IntStream stream = IntStream.range(1, 100);
        stream.forEach(consumer);
    }

    private IntConsumer consumer = (i) -> {
        try {
            CompletableFuture<Integer> future = invokeMultiplyAsync(i, i + 1);
            int result = future.get();
            assertThat(result).isNotNegative();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    };

    public CompletableFuture<Integer> invokeMultiplyAsync(int firstArg, int secondArg) throws InterruptedException {
        int result = networkService.multiply(firstArg, secondArg);
        return CompletableFuture.completedFuture(result);
    }
}
