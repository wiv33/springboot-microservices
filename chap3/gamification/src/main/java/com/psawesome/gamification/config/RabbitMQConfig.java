package com.psawesome.gamification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * 큐를 익스체인지에 바인딩 하는 개념을 이해해야 한다.
 *
 *  - 구독자는 메시지를 소비하는 큐를 생성해야 한다.
 *  - 해당 메시지는 라우팅 키(현재 app에선 multiplication.solved)를 이용해 익스체인지에 발행된다.
 *      (토픽을 유연하게 교환하는데 도움이 된다.)
 *  - 익스체인지를 통해 전송되는 모든 메시지는 라우팅 키로 태그를 붙이고
 *  - 소비자는 큐를 익스체인지에 바인딩할 때 라우팅
 *      키 또는 패턴(this app => multiplication.*)을 이용해 큐로 전송되는 메시지를 선택한다.
 *
 * package: com.psawesome.gamification.config
 * author: PS
 * DATE: 2020-01-29 수요일 06:52
 */
@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

    /*
        ## RabbitMQ 설정
        1. multiplication.exchange=multiplication_exchange
        2. multiplication.solved.key=multiplication.solved
        3. multiplication.queue=gamification_multiplication_queue
        4. multiplication.anything.routing-key=multiplication.*

        [1] social-multiplication 에 정의된 exchange와 동일한 key를 사용해야 한다.
        [2] social-multiplication 에 routingKey가 solved로 지정되어 있기 때문에
            [4]에서 정규표현 패턴으로 라우팅 키를 매칭시킨다.
        [3] 큐 이름 선언
     */

    // START::한 묶음
    /**
     * TopicExchange 등록
     * @param exchangeName
     * @return
     */
    @Bean
    public TopicExchange multiplicationTopicExchange(
            @Value("${multiplication.exchange}") final String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    /**
     * core.Queue 등록
     * @param queueName
     * @return
     */
    @Bean
    public Queue gamificationMultiplicationQueue(@Value("${multiplication.queue}") final String queueName) {
        return new Queue(queueName, true); // == new Queue(queueName)
    }

    /**
     * binding
     * Queue - TopicExchange - routingKey
     * @param queue
     * @param topicExchange
     * @param routingKey
     * @return
     */
    @Bean
    public Binding binding(final Queue queue, TopicExchange topicExchange,
                            @Value("${multiplication.anything.routing-key}") final String routingKey) {
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }
    // END::한 묶음


    // START::구독자 JSON 역직렬화 설정
    /*
        이 서비스에서는 메시지를 전송하지 않기 때문에
        RabbitTemplate 이 아닌
        @RabbitListener 를 사용.
        MappingJackson2MessageConverter 로
        RabbitListenerEndpointRegistrar 를 구성한다.

     */
    /**
     * 소비자 메시지 컨버터 등록
     * @return
     */
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    /**
     *
     * @return
     */
    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
