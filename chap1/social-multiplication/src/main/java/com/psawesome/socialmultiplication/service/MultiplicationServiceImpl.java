package com.psawesome.socialmultiplication.service;

import com.psawesome.socialmultiplication.domain.Multiplication;
import com.psawesome.socialmultiplication.domain.MultiplicationResultAttempt;
import org.springframework.stereotype.Service;

/**
 * package: com.psawesome.socialmultiplication.service
 * author: PS
 * DATE: 2020-01-22 수요일 22:09
 */
@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    private RandomGeneratorService randomGeneratorService;

    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService) {
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
        Multiplication multiplication = resultAttempt.getMultiplication();
        return multiplication.getFactorA() * multiplication.getFactorB() == resultAttempt.getResultAttempt();
    }
}
