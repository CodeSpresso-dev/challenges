package mehdi.challenge.owlestic.microservice.orderservice.query;

import mehdi.challenge.owlestic.microservice.orderservice.core.data.OrderEntity;
import mehdi.challenge.owlestic.microservice.orderservice.core.data.OrderRepository;
import mehdi.challenge.owlestic.microservice.orderservice.core.events.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void eventHandler(OrderCreatedEvent orderCreatedEvent) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent, orderEntity);

        orderRepository.save(orderEntity);
    }
}
