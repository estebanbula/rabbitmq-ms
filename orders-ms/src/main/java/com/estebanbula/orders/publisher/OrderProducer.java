package com.estebanbula.orders.publisher;

import com.estebanbula.orders.config.RabbitMqConfig;
import com.estebanbula.orders.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendEvent(OrderEvent orderEvent) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE,
                RabbitMqConfig.ROUTING_KEY_EVENTS_GENERAL, orderEvent);
    }
}
