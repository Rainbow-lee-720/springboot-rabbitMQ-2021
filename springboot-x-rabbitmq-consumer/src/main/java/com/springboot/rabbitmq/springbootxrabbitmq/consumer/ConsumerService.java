package com.springboot.rabbitmq.springbootxrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = "queue_test")
    public void receiveMessage(Message message) {
        String messageStr = new String(message.getBody());
        logger.info("message: {}", messageStr);
    }

}
