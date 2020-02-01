package com.psawesome.gamification.controller;

import com.psawesome.gamification.domain.LeaderBoardRow;
import com.psawesome.gamification.service.LeaderBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Gamification 리더보드 서비스의 REST API
 *
 * package: com.psawesome.gamification.controller
 * author: PS
 * DATE: 2020-01-29 수요일 06:27
 */
@RestController
@RequestMapping("/leaders")
public class LeaderBoardController {
    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
