package com.psawesome.socialmultiplication.service;

import com.psawesome.socialmultiplication.domain.Multiplication;
import com.psawesome.socialmultiplication.domain.MultiplicationResultAttempt;
import com.psawesome.socialmultiplication.domain.User;
import com.psawesome.socialmultiplication.repository.MultiplicationResultAttemptRepository;
import com.psawesome.socialmultiplication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * package: com.psawesome.socialmultiplication.service
 * author: PS
 * DATE: 2020-01-22 수요일 22:09
 */
@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    private RandomGeneratorService randomGeneratorService;
    private UserRepository userRepository;
    private MultiplicationResultAttemptRepository attemptRepository;

    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService, MultiplicationResultAttemptRepository attemptRepository, UserRepository userRepository) {
        this.randomGeneratorService = randomGeneratorService;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {

        // 해당 사용자 존재여부 확인
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

        // 초기 값이 true라면 조작된 데이터로 IllegalException을 던짐
        Assert.isTrue(!resultAttempt.isCorrect(), "채점한 상태로 보낼 수 없습니다!");

        // 답안 채점
        boolean isCorrect = resultAttempt.getMultiplication().getFactorA() * resultAttempt.getMultiplication().getFactorB() == resultAttempt.getResultAttempt();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(user.orElse(resultAttempt.getUser()), resultAttempt.getMultiplication(), resultAttempt.getResultAttempt(), isCorrect);

        // 답안 저장
        attemptRepository.save(checkedAttempt);

        return isCorrect;
    }
}
