package com.psawesome.socialmultiplication.controller;

import com.psawesome.socialmultiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * package: com.psawesome.socialmultiplication.controller
 * author: PS
 * DATE: 2020-01-22 수요일 23:54
 */
@RestController
public class MultiplicationController {

    private MultiplicationService service;

    @Autowired
    public MultiplicationController(final MultiplicationService service) {
        this.service = service;
    }
}
