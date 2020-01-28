package com.psawesome.gamification.service;

import com.psawesome.gamification.domain.GameStats;
import com.psawesome.gamification.domain.ScoreCard;
import com.psawesome.gamification.repository.BadgeCardRepository;
import com.psawesome.gamification.repository.ScoreCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;

/**
 * package: com.psawesome.gamification.service
 * author: PS
 * DATE: 2020-01-28 화요일 21:04
 */
@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;

    GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
    }

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        if (correct) {
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            scoreCardRepository.save(scoreCard);
            log.info("사용자 ID {}, 점수 {} 점, 답안 ID {}", userId, scoreCard.getScore(), attemptId);

            processForBadges(userId, attemptId);
        }
        return null;
    }

    private void processForBadges(Long userId, Long attemptId) {

    }
}
