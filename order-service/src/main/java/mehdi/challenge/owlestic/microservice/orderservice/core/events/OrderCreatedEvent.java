package mehdi.challenge.owlestic.microservice.orderservice.core.events;

import lombok.Data;
import mehdi.challenge.owlestic.microservice.orderservice.core.model.OrderStatus;

import java.time.LocalDateTime;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String customerId;
    private LocalDateTime createdAt;
    private OrderStatus orderStatus;
}
