package com.psawesome.socialmultiplication.controller;

import com.psawesome.socialmultiplication.domain.Multiplication;
import com.psawesome.socialmultiplication.service.MultiplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * package: com.psawesome.socialmultiplication.controller
 * author: PS
 * DATE: 2020-01-22 수요일 23:54
 */
@Slf4j
@RestController
@RequestMapping("/multiplication")
public final class MultiplicationController {

    private final MultiplicationService service;
    private final int serverPort;

    @Autowired
    public MultiplicationController(final MultiplicationService service,
                                    final @Value("${server.port}") int serverPort) {
        this.service = service;
        this.serverPort = serverPort;
    }

    @GetMapping("/random")
    public Multiplication getRandomMultiplication() {
        log.info("Running Multiplication Server port {}, Time: {}", serverPort, LocalDateTime.now(Clock.systemDefaultZone()));
        return service.createRandomMultiplication();
    }
}
