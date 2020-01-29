package com.psawesome.gamification.event;

import com.psawesome.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 이벤트를 받고 연관된 비즈니스 로직을 동작시킨다.
 *
 * package: com.psawesome.gamification.event
 * author: PS
 * DATE: 2020-01-29 수요일 22:53
 */
@Slf4j
@Component
public class EventHandler {
    private GameService gameService;

    public EventHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${multiplication.queue}")
    public void handleMultiplicationSolved(final MultiplicationSolvedEvent event) {
        log.info("Multiplication Solved Event 수신: {}", event.getMultiplicationResultAttemptId());
        try {
            gameService.newAttemptForUser(event.getUserId(),
                    event.getMultiplicationResultAttemptId(),
                    event.isCorrect());
        } catch (final Exception e) {
            log.error("MultiplicationSolvedEvent 처리 시 에러", e);
            // 해당 이벤트가 다시 큐로 들어가거나 두 번 처리되지 않도록 예외 발생
            throw new AmqpRejectAndDontRequeueException(e);
        }
        /*
            기본적으로 문제가 생겼을 때 해당 이벤트가 반복적으로 큐에 들어가게 됨.
            로직을 예외처리로 감싸고
            AmqpRejectAndDontRequeueException을 발생시켜 바로 이벤트 거부 상태로 만듦.
            데드 레터 익스체인지 (dead letter exchange)를 구성하고 전송 실패 메시지 처리의 방안을 모색해야 함.
            - 재전송, 로깅, 알람 발생 등
         */
    }
}
