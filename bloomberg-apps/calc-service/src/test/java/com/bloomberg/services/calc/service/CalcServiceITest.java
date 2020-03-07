package com.bloomberg.services.calc.service;

import com.bloomberg.services.calc.model.MultiplyRequest;
import com.bloomberg.services.calc.model.MultiplyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CalcServiceITest {
    @Autowired
    private CalcService calcService;

    @Test
    public void multiply_withZeroNumbers_throwsIllegalArgumentException() {
        MultiplyRequest request = prepareMultiplyRequest(0, 0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calcService.multiply(request);
        });

        String expectedMessage = "Argument must not be zero or a negative number.";
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void multiply_withNegativeNumbers_throwsIllegalArgumentException() {
        MultiplyRequest request = prepareMultiplyRequest(-1, -2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calcService.multiply(request);
        });

        String expectedMessage = "Argument must not be zero or a negative number.";
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void multiply_withPositiveNumbers_returnMultiplication() {
        MultiplyRequest request = prepareMultiplyRequest(2, 3);
        MultiplyResponse response = calcService.multiply(request);
        assertThat(response).isNotNull();
        assertThat(response.getResult()).isNotNull().isEqualTo(6);
    }

    private MultiplyRequest prepareMultiplyRequest(final int firstArg, final int secondArg) {
        return MultiplyRequest.builder()
                .firstArg(firstArg)
                .secondArg(secondArg)
                .build();
    }
}
