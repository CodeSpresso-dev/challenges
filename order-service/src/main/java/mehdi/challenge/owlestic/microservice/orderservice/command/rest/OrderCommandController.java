package mehdi.challenge.owlestic.microservice.orderservice.command.rest;

import mehdi.challenge.owlestic.microservice.orderservice.command.commands.CreateOrderCommand;
import mehdi.challenge.owlestic.microservice.orderservice.core.model.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderCommandController {

    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderCreateRest orderCreate) {

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .customerId(orderCreate.getCustomerId())
                .orderStatus(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        String returnedValue = commandGateway.sendAndWait(createOrderCommand);

        return returnedValue;
    }
}
