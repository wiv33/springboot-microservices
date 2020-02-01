package com.psawesome.gamification.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.psawesome.gamification.client.MultiplicationResultAttemptDeserializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * package: com.psawesome.gamification.client.dto
 * author: PS
 * DATE: 2020-01-28 화요일 21:13
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public class MultiplicationResultAttempt {
    private final String userAlias;

    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;

    private final boolean correct;

    // JSON/JPA 를 위한 빈 생성자
    MultiplicationResultAttempt() {
        userAlias = null;
        multiplicationFactorA = -1;
        multiplicationFactorB = -1;
        resultAttempt = -1;
        correct = false;
    }
}