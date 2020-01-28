package com.psawesome.gamification.service;

import com.psawesome.gamification.domain.LeaderBoardRow;

import java.util.List;

/**
 * package: com.psawesome.gamification.service
 * author: PS
 * DATE: 2020-01-28 화요일 19:45
 */
public interface LeaderBoardService {
    /**
     * 최고 점수 사용자와 함께 현재 리더 보드를 검색
     * @return 최고 점수와 사용자
     */
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
