package com.psawesome.gamification.client;

import com.psawesome.gamification.client.dto.MultiplicationResultAttempt;

/**
 * package: com.psawesome.gamification.client
 * author: PS
 * DATE: 2020-01-28 화요일 21:11
 */
public interface MultiplicationResultAttemptClient {

    MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(final Long multiplicationId);
}
