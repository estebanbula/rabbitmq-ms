package com.estebanbula.orders.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class RabbitMqConfig {

    @Value("${adapters.rabbitmq.host}")
    private String rabbitHost;
    @Value("${adapters.rabbitmq.username}")
    private String rabbitUsername;
    @Value("${adapters.rabbitmq.password}")
    private String rabbitPwd;
    @Value("${adapters.rabbitmq.port}")
    private Integer rabbitPort;
    @Value("${adapters.rabbitmq.virtualhost}")
    private String virtualHost;

    public static final String TOPIC_EXCHANGE = "orders-email-topic-exchange";
    public static final String ORDERS_EVENTS_GENERAL = "orders.events.general";
    public static final String ORDERS_ROUTING_KEY_EVENTS = "orders.routing.key.events.#";
    public static final String EMAIL_EVENTS_GENERAL = "email.events.general";
    public static final String EMAIL_ROUTING_KEY_EVENTS = "email.routing.key.events.#";

    @Bean
    public Queue orderQueue() {
        return new Queue(ORDERS_EVENTS_GENERAL);
    }
    @Bean
    public Queue emailQueue() {
        return new Queue(ORDERS_EVENTS_GENERAL);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingOrdersQueue() {
        return BindingBuilder
                .bind(orderQueue())
                .to(topicExchange())
                .with(ORDERS_ROUTING_KEY_EVENTS);
    }

    @Bean
    public Binding bindingEmailQueue() {
        return BindingBuilder
                .bind(emailQueue())
                .to(topicExchange())
                .with(EMAIL_ROUTING_KEY_EVENTS);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Connection rabbitConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitHost);
        connectionFactory.setPort(rabbitPort);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPwd);
        return connectionFactory.newConnection();
    }

}
