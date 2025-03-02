package mehdi.challenge.owlestic.microservice.orderservice.command.commands;


import lombok.Builder;
import lombok.Data;
import mehdi.challenge.owlestic.microservice.orderservice.core.model.OrderStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Builder
@Data
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
    private final String customerId;
    private final LocalDateTime createdAt;
    private final OrderStatus orderStatus;
}
