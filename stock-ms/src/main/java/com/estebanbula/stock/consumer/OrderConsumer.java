package com.estebanbula.stock.consumer;

import com.estebanbula.stock.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${adapters.rabbitmq.queue.orders}")
    public void consumeEvent(OrderEvent event) {
        logger.info(String.format("Order event received: %s", event.toString()));

    }
}
