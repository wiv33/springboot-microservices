package com.psawesome.socialmultiplication.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * package: com.psawesome.socialmultiplication.event
 * author: PS
 * DATE: 2020-01-27 월요일 18:52
 * <p>
 * 이벤트는 이미 발생한 과거의 일이어야 하고, 일반화돼야 한다.(구독자에 대해서 알지 못한다.)
 * 해결된 곱셈 답안과 구독자가 곱셈 마이크로서비스와 무관하다는 것을 알아본다.
 *
 * 시스템에서 {@link com.psawesome.socialmultiplication.domain.Multiplication}
 * 문제가 해결됐다는 사실을 모델링한 이벤트
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {

    private final Long multiplicationResultsAttemptId;
    private final Long userId;
    private final boolean correct;
}
