package com.psawesome.socialmultiplication.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * package: com.psawesome.socialmultiplication.service
 * author: PS
 * DATE: 2020-01-22 수요일 22:34
 */
public class RandomGeneratorServiceTest {

    RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp() {
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    public void setRandomGeneratorServiceTest() {
        List<Integer> collect = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(toList());
        assertThat(collect).containsOnlyElementsOf(IntStream.range(11, 100).boxed().collect(toList()));
    }
}