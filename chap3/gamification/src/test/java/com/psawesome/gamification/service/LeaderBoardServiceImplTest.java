package com.psawesome.gamification.service;

import com.psawesome.gamification.domain.LeaderBoardRow;
import com.psawesome.gamification.repository.ScoreCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * package: com.psawesome.gamification.service
 * author: PS
 * DATE: 2020-01-29 수요일 06:00
 */
public class LeaderBoardServiceImplTest {

    private LeaderBoardService leaderBoardService;

    @Mock
    ScoreCardRepository scoreCardRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        leaderBoardService = new LeaderBoardServiceImpl(scoreCardRepository);
    }

    @Test
    public void getCurrentLeaderBoardTest() {
        // given
        Long userId = 1L;
        LeaderBoardRow leaderBoardRow = new LeaderBoardRow(userId, 300L);
        List<LeaderBoardRow> expectedLeaderBoard = Collections.singletonList(leaderBoardRow);

        given(scoreCardRepository.findFirst10()).willReturn(expectedLeaderBoard);

        // when
        List<LeaderBoardRow> currentLeaderBoard = leaderBoardService.getCurrentLeaderBoard();

        // then
        assertThat(currentLeaderBoard).isEqualTo(expectedLeaderBoard);
    }
}