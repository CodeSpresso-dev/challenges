package mehdi.challenge.owlestic.microservice.orderservice.command;

import mehdi.challenge.owlestic.microservice.orderservice.command.commands.CreateOrderCommand;
import mehdi.challenge.owlestic.microservice.orderservice.core.events.OrderCreatedEvent;
import mehdi.challenge.owlestic.microservice.orderservice.core.model.OrderStatus;
import mehdi.challenge.owlestic.microservice.orderservice.external.CustomerClient;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private OrderStatus orderStatus;

    public OrderAggregate() {

    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {



        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);

        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.orderId = orderCreatedEvent.getOrderId();
        this.customerId = orderCreatedEvent.getCustomerId();
        this.createdAt = orderCreatedEvent.getCreatedAt();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
    }
}
