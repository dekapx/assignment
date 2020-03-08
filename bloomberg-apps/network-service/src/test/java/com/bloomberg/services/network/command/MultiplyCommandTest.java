package com.bloomberg.services.network.command;

import com.bloomberg.services.network.model.MultiplyRequest;
import com.bloomberg.services.network.model.MultiplyResponse;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.VerificationsInOrder;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplyCommandTest {
    @Tested
    private MultiplyCommand multiplyCommand;

    @Injectable
    private String serviceUrl;

    @Injectable
    private RestTemplate restTemplate;

    @Mocked
    private MultiplyRequest multiplyRequestMock;

    @Mocked
    private MultiplyResponse multiplyResponseMock;

    @Mocked
    private ResponseEntity responseEntityMock;

    @Test
    public void execute_withValidMultiplyRequest_returnMultiplyResponse() {
        new Expectations() {
            {
                restTemplate.postForEntity(anyString, multiplyRequestMock, MultiplyResponse.class);
                result = responseEntityMock;

                responseEntityMock.getBody();
                result = multiplyResponseMock;
            }
        };

        MultiplyResponse response = multiplyCommand.execute(multiplyRequestMock);
        assertThat(response).isNotNull().isInstanceOf(MultiplyResponse.class);

        new VerificationsInOrder() {{
            restTemplate.postForEntity(anyString, multiplyRequestMock, MultiplyResponse.class);
            times = 1;

            responseEntityMock.getBody();
            times = 1;
        }};
    }
}
