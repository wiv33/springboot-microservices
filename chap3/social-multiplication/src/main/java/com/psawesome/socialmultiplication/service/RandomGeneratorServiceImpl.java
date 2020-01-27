package com.psawesome.socialmultiplication.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * package: com.psawesome.socialmultiplication.service
 * author: PS
 * DATE: 2020-01-22 수요일 22:47
 */
@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {
    public static final int MINIMUM_FACTOR = 11;
    public static final int MAXIMUM_FACTOR = 99;
    @Override
    public int generateRandomFactor() {
        return ThreadLocalRandom.current().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
