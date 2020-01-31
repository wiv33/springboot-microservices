package com.psawesome.gamification.event;

import lombok.*;

import java.io.Serializable;

/**
 * package: com.psawesome.gamification.event
 * author: PS
 * DATE: 2020-01-29 수요일 23:03
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {

    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;

    public MultiplicationSolvedEvent() {
        this(null, null, false);
    }
}
