package com.psawesome.socialmultiplication.controller;

import com.psawesome.socialmultiplication.domain.Multiplication;
import com.psawesome.socialmultiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package: com.psawesome.socialmultiplication.controller
 * author: PS
 * DATE: 2020-01-22 수요일 23:54
 */
@RestController
@RequestMapping("/multiplication")
public final class MultiplicationController {

    private MultiplicationService service;

    @Autowired
    public MultiplicationController(final MultiplicationService service) {
        this.service = service;
    }

    @GetMapping("/random")
    public Multiplication getRandomMultiplication() {
        return service.createRandomMultiplication();
    }
}
