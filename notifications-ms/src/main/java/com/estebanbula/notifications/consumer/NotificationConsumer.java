package com.estebanbula.notifications.consumer;

import com.estebanbula.notifications.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    @RabbitListener(queues = "${adapters.rabbitmq.queue.email}")
    public void consumeEvent(OrderEvent event) {
        logger.info(String.format("Order event received: %s", event.toString()));

    }
}
