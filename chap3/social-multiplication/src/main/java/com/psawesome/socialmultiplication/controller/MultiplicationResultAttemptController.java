package com.psawesome.socialmultiplication.controller;

import com.psawesome.socialmultiplication.domain.MultiplicationResultAttempt;
import com.psawesome.socialmultiplication.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * package: com.psawesome.socialmultiplication.controller
 * author: PS
 * DATE: 2020-01-23 목요일 00:13
 */
@RestController
public final class MultiplicationResultAttemptController {
    private final MultiplicationService service;

    public MultiplicationResultAttemptController(MultiplicationService service) {
        this.service = service;
    }

    @PostMapping("/results")
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt attempt) {
        boolean isCorrect = service.checkAttempt(attempt);
        MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(), isCorrect);
        return ResponseEntity.ok(attemptCopy);
    }

    @GetMapping("/results")
    ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(service.getStateForUser(alias));
    }

    @GetMapping("/results/{resultId}")
    public ResponseEntity<MultiplicationResultAttempt> getResultById(final @PathVariable("resultId") Long resultId) {
        return ResponseEntity.ok(service.getResultById(resultId));
    }

    @RequiredArgsConstructor
    @Getter
    @NoArgsConstructor(force = true)
    static final class ResultResponse {
        private final boolean correct;
    }
}
