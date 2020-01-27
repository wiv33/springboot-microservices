package com.psawesome.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * package: com.psawesome.gamification.domain
 * author: PS
 * DATE: 2020-01-27 월요일 21:20
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class LeaderBoardRow {

    private final Long userId;
    private final Long totalScore;

    public LeaderBoardRow() {
        this(0L, 0L);
    }
}
