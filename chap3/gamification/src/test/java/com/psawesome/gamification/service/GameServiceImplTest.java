package com.psawesome.gamification.service;

import com.psawesome.gamification.client.MultiplicationResultAttemptClient;
import com.psawesome.gamification.client.dto.MultiplicationResultAttempt;
import com.psawesome.gamification.domain.Badge;
import com.psawesome.gamification.domain.BadgeCard;
import com.psawesome.gamification.domain.GameStats;
import com.psawesome.gamification.domain.ScoreCard;
import com.psawesome.gamification.repository.BadgeCardRepository;
import com.psawesome.gamification.repository.ScoreCardRepository;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

/**
 * package: com.psawesome.gamification.service
 * author: PS
 * DATE: 2020-01-28 화요일 21:05
 */
public class GameServiceImplTest {

    private GameServiceImpl gameService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Mock
    private BadgeCardRepository badgeCardRepository;

    @Mock
    private MultiplicationResultAttemptClient multiplicationClient;

    @Before
    public void setUp() throws Exception {
        // initMocks 를 호출해 Mockito 가 어노테이션을 처리하도록 지시
        MockitoAnnotations.initMocks(this);
        gameService = new GameServiceImpl(scoreCardRepository, badgeCardRepository, multiplicationClient);

        // given - 기본적으로 행운의 숫자를 포함하지 않는 답안
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                "john_doe", 20, 70, 1400, true);
        given(multiplicationClient.retrieveMultiplicationResultAttemptById(anyLong()))
                .willReturn(attempt);
    }

    @Test
    @Description("처음 올바른 답안을 제출했을 때")
    public void processFirstCorrectAttemptTest() {
        // given
        Long userId = 3L;
        Long attemptId = 8L;
        int totalScore = 10;

        ScoreCard scoreCard = new ScoreCard(userId, attemptId);
        given(scoreCardRepository.getTotalScoreForUser(userId).orElse(10)).willReturn(totalScore);
        given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId)).willReturn(Collections.singletonList(scoreCard));
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(Collections.EMPTY_LIST);


        // when
        GameStats iteration = gameService.newAttemptForUser(userId, attemptId, true);

        // then
        assertThat(iteration.getScore()).isEqualTo(ScoreCard.DEFAULT_SCORE);
        assertThat(iteration.getBadges()).containsOnly(Badge.FIRST_WON);

    }

    @Test
    @Description("처음 100점을 얻었을 때 브론즈 획득 테스트")
    public void processCorrectAttemptForScoreBadgeTest() {
        // given
        Long userId = 3L;
        Long attemptId = 33L;
        int totalScore = 100;

        BadgeCard firstWonBadge = new BadgeCard(userId, Badge.FIRST_WON);
        given(scoreCardRepository.getTotalScoreForUser(userId).orElse(10)).willReturn(totalScore);

        // 방금 얻은 점수 카드를 반환
        getNScoreCards()
                .andThen(given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId))::willReturn)
                .apply(3L, 10L);

        // 첫 번째 정답 배지는 이미 존재
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
                .willReturn(Collections.singletonList(firstWonBadge));

        // when
        GameStats iteration = gameService.newAttemptForUser(userId, attemptId, true);

        // then
        assertThat(iteration.getScore()).isEqualTo(ScoreCard.DEFAULT_SCORE);
        assertThat(iteration.getBadges()).containsOnly(Badge.BRONZE_MULTIPLICATOR);
    }

    @Test
    @Description("행운의 숫자 배지 획득")
    public void processCorrectAttemptForLuckyNumberBadgeTest() {
        // given
        Long userId = 3L;
        Long attemptId = 33L;
        int totalScore = 10;

        BadgeCard firstBadge = new BadgeCard(userId, Badge.FIRST_WON);
        given(scoreCardRepository.getTotalScoreForUser(userId).orElse(10)).willReturn(totalScore);
        getNScoreCards()
                .andThen(given(scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId))::willReturn)
                .apply(userId, 1L);
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(Collections.singletonList(firstBadge));

        // 행운의 숫자가 포함된 답안
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt("john", 42, 10, 420, true);
        given(multiplicationClient.retrieveMultiplicationResultAttemptById(attemptId)).willReturn(attempt);


        // when
        GameStats iteration = gameService.newAttemptForUser(userId, attemptId, true);

        // then
        assertThat(iteration.getScore()).isEqualTo(ScoreCard.DEFAULT_SCORE);
        assertThat(iteration.getBadges()).containsOnly(Badge.LUCKY_NUMBER);
    }

    private BiFunction<Long, Long, List<ScoreCard>> getNScoreCards() {
        return (userId1, scoreCardNumber) -> LongStream.iterate(0L, value -> value++)
                .limit(scoreCardNumber)
                .mapToObj(v -> new ScoreCard(userId1, v))
                .collect(Collectors.toList());
    }
}