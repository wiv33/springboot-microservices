package com.psawesome.socialmultiplication.controller;

import com.psawesome.socialmultiplication.domain.MultiplicationResultAttempt;
import com.psawesome.socialmultiplication.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * package: com.psawesome.socialmultiplication.controller
 * author: PS
 * DATE: 2020-01-23 목요일 00:13
 */
@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {
    private final MultiplicationService service;

    public MultiplicationResultAttemptController(MultiplicationService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<ResultResponse> postResult(@RequestBody MultiplicationResultAttempt attempt) {
        return ResponseEntity.ok(new ResultResponse(service.checkAttempt(attempt)));
    }

    @RequiredArgsConstructor
    @Getter
    @NoArgsConstructor(force = true)
    static final class ResultResponse {
        private final boolean correct;
    }
}
