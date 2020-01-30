package com.psawesome.gamification.client;

import com.psawesome.gamification.client.dto.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * package: com.psawesome.gamification.client
 * author: PS
 * DATE: 2020-01-28 화요일 21:41
 */
@Component
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient {
    private final RestTemplate restTemplate;
    private final String multiplicationHost;

    public MultiplicationResultAttemptClientImpl(final RestTemplate restTemplate,
                                                 @Value("${multiplication.host}") final String multiplicationHost) {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }

    @Override
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(Long multiplicationId) {
        return restTemplate.getForObject(multiplicationHost + "/restuls/" + multiplicationId, MultiplicationResultAttempt.class);
    }
}
