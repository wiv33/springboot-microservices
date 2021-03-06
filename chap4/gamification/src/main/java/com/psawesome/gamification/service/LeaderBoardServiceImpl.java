package com.psawesome.gamification.service;

import com.psawesome.gamification.domain.LeaderBoardRow;
import com.psawesome.gamification.repository.ScoreCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * package: com.psawesome.gamification.service
 * author: PS
 * DATE: 2020-01-29 수요일 06:00
 */
@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreCardRepository scoreCardRepository;

    public LeaderBoardServiceImpl(ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
