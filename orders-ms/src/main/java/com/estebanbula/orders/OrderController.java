package com.estebanbula.orders;

import com.estebanbula.orders.model.Order;
import com.estebanbula.orders.model.OrderEvent;
import com.estebanbula.orders.publisher.OrderProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping()
    public String placeOrder(@RequestBody Order order) throws JsonProcessingException {
        order.setOrderId(UUID.randomUUID().toString());
        ObjectMapper objectMapper = new ObjectMapper();

        orderProducer.sendEvent(new OrderEvent().toBuilder()
                .status("PENDING")
                .message("Order is in pending status!")
                .order(order).build());

        return String.format("Event emitted, %s", objectMapper.writeValueAsString(order));
    }
}
