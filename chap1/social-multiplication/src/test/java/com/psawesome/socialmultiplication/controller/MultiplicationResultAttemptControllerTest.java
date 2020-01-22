package com.psawesome.socialmultiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psawesome.socialmultiplication.domain.Multiplication;
import com.psawesome.socialmultiplication.domain.MultiplicationResultAttempt;
import com.psawesome.socialmultiplication.domain.User;
import com.psawesome.socialmultiplication.service.MultiplicationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static com.psawesome.socialmultiplication.controller.MultiplicationResultAttemptController.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * package: com.psawesome.socialmultiplication.controller
 * author: PS
 * DATE: 2020-01-23 목요일 00:02
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class MultiplicationResultAttemptControllerTest {

    @MockBean
    private MultiplicationService service;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<MultiplicationResultAttempt> jsonResult;
    private JacksonTester<ResultResponse> jsonResponse;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }

    private void genericParameterizedTest(final boolean correct) throws Exception {
        // given
        given(service.checkAttempt(any(MultiplicationResultAttempt.class)))
                .willReturn(correct);

        User user = new User("John");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500);

        // when
        MockHttpServletResponse response = mvc.perform(post("/results")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonResult.write(attempt).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new ResultResponse(correct)).getJson());
    }
}
