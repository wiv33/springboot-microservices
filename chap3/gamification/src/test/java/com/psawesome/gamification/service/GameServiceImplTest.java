package com.psawesome.gamification.service;
import com.psawesome.gamification.client.MultiplicationResultAttemptClient;
import com.psawesome.gamification.client.dto.MultiplicationResultAttempt;
import com.psawesome.gamification.repository.BadgeCardRepository;
import com.psawesome.gamification.repository.ScoreCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
}