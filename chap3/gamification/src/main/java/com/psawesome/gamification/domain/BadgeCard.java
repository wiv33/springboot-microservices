package com.psawesome.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * package: com.psawesome.gamification.domain
 * author: PS
 * DATE: 2020-01-27 월요일 20:46
 * <p>
 * 배지와 사용자를 연결하는 클래스
 * 사용자가 배지를 획득한 순간의 타임스탬프를 포함
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public final class BadgeCard {
    @Id
    @GeneratedValue
    @Column("BADGE_ID")
    private final Long badgeId;

    private final Long userId;
    private final long badgeTimestamp;

    @Enumerated(EnumType.STRING)
    private final Badge badge;

    public BadgeCard() {
        this(null, null, 0, null);
    }

    public BadgeCard(final Long userId, final Badge badge) {
        this(null, userId, System.currentTimeMillis(), badge);
    }
}
