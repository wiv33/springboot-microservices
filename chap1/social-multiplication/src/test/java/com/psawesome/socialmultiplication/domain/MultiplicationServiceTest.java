package com.psawesome.socialmultiplication.domain;

import com.psawesome.socialmultiplication.service.MultiplicationService;
import com.psawesome.socialmultiplication.service.RandomGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * package: com.psawesome.socialmultiplication.domain
 * author: PS
 * DATE: 2020-01-22 수요일 21:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MultiplicationServiceTest {

    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    public void name() {
        // given
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}