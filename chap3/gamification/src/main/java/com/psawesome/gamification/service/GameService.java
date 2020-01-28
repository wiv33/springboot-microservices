package com.psawesome.gamification.service;

import com.psawesome.gamification.domain.GameStats;

/**
 * package: com.psawesome.gamification.service
 * author: PS
 * DATE: 2020-01-28 화요일 19:42
 */
public interface GameService {

    /**
     * 주어진 사용자가 제출한 답안을 처리
     *
     * @param userId
     *         사용자 ID
     * @param attemptId
     *         필요한 경우 추가로 데이터를 조회하기 위한 답안 ID
     * @param correct
     *         답안의 정답 여부
     * @return 새로운 점수와 배지 카드를 포함한 {@link GameStats} 객체
     */
    GameStats newAttemptForUser(final Long userId, final Long attemptId, final boolean correct);
}
