package com.springboot.rabbitmq.springbootxrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 */
@Configuration
public class RabbitMQConfig {

    public static final String TOPIC_EXCHANGE_TEST = "topic_exchange_test";

    public static final String QUEUE_TEST = "queue_test";

    public static final String ROUTE_KEY_TEST = "rabbit.#";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean(value = "topicExchangeTest")
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_TEST).durable(true).build();
    }

    @Bean(value = "queueTest")
    public Queue queueTest() {
        return QueueBuilder.durable(QUEUE_TEST).build();
    }

    @Bean(value = "bindingBuilder")
    public Binding bindingTest(@Qualifier(value = "queueTest") Queue queue,
                               @Qualifier(value = "topicExchangeTest") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTE_KEY_TEST).noargs();
    }

    @Bean(value = "bindingConstruct")
    public Binding bindingTest() {
        return new Binding(QUEUE_TEST, Binding.DestinationType.QUEUE, TOPIC_EXCHANGE_TEST, ROUTE_KEY_TEST, null);
    }

}
