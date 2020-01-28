package com.psawesome.gamification.controller;

import com.psawesome.gamification.domain.GameStats;
import com.psawesome.gamification.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Gamification 사용자 통계 서비스의 REST API
 * <p>
 * package: com.psawesome.gamification.controller
 * author: PS
 * DATE: 2020-01-29 수요일 06:33
 */
@RestController
@RequestMapping("/stats")
public class UserStatsController {
    private final GameService gameService;

    public UserStatsController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    private GameStats getStatsForUser(@RequestParam("userId") final Long userId) {
        return gameService.retrieveStatsForUser(userId);
    }
}
