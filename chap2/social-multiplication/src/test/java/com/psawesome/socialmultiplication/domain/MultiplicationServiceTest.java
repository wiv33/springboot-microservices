package com.psawesome.socialmultiplication.domain;

import com.psawesome.socialmultiplication.repository.MultiplicationResultAttemptRepository;
import com.psawesome.socialmultiplication.repository.UserRepository;
import com.psawesome.socialmultiplication.service.MultiplicationService;
import com.psawesome.socialmultiplication.service.MultiplicationServiceImpl;
import com.psawesome.socialmultiplication.service.RandomGeneratorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * package: com.psawesome.socialmultiplication.domain
 * author: PS
 * DATE: 2020-01-22 수요일 21:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MultiplicationServiceTest {

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    private MultiplicationService multiplicationService;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository);
    }

    @Test
    public void createRandomMultiplicationTest() {
        // given
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
//        assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John");

        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);

        given(userRepository.findByAlias("john")).willReturn(Optional.empty());

        // when
        boolean b = multiplicationService.checkAttempt(attempt);

        assertThat(b).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("John");

        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3001, false);
        given(userRepository.findByAlias("john")).willReturn(Optional.empty());
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3051, false);

        List<MultiplicationResultAttempt> latestAttempts = Arrays.asList(attempt, attempt1);
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("john")).willReturn(latestAttempts);

        // when
        List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationService.getStateForUser("john");

        //assert
        assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
    }
}