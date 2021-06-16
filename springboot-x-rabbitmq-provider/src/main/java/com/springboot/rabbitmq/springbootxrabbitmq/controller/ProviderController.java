package com.springboot.rabbitmq.springbootxrabbitmq.controller;

import com.springboot.rabbitmq.springbootxrabbitmq.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbit/provider")
public class ProviderController {

    private static Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send")
    public String sendRabbitMessage(@RequestParam(value = "message") String message,
                                    @RequestParam(value = "routeKey") String routeKey) {
        logger.info("request:{}, message:{}, routekey:{}", "send", message, routeKey);
        String x_test_id = "1234";
        CorrelationData correlationId = new CorrelationData(x_test_id);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_TEST, routeKey,
                message, correlationId);
        return "success";
    }

}
