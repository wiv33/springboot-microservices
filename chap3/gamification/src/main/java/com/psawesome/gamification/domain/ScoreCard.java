package com.psawesome.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * package: com.psawesome.gamification.domain
 * author: PS
 * DATE: 2020-01-27 월요일 20:54
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public final class ScoreCard {

    // 명시되지 않은 경우 이 카드에 할당되는 기본 점수
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue
    @Column("CARD_ID")
    private final Long cardId;

    @Column("USER_ID")
    private final Long userId;

    @Column("ATTEMPT_ID")
    private final Long attemptId;

    @Column("SCORE_TS")
    private final long scoreTimestamp;

    @Column("SCORE")
    private final int score;

    public ScoreCard() {
        this(null, null, null, 0, 0);
    }

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
