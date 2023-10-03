package com.estebanbula.notifications.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
