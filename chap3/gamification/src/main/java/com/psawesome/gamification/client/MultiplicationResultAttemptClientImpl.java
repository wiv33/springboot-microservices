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
                                                 @Value("${multiplicationHost}") final String multiplicationHost) {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }

    // 해당 메서드는 시간차로 인해 100% 안전하지 않다.
    @Override
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(Long multiplicationResultAttemptId) {
        return restTemplate.getForObject(multiplicationHost + "/results/{resultId}", MultiplicationResultAttempt.class, multiplicationResultAttemptId);
    }
}
