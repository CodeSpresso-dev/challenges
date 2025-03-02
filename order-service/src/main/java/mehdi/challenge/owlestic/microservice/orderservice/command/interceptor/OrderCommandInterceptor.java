package mehdi.challenge.owlestic.microservice.orderservice.command.interceptor;

import mehdi.challenge.owlestic.microservice.orderservice.command.commands.CreateOrderCommand;
import mehdi.challenge.owlestic.microservice.orderservice.exception.ResourceNotFoundException;
import mehdi.challenge.owlestic.microservice.orderservice.external.ExternalServicesManagement;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class OrderCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private final ExternalServicesManagement externalServicesManagement;

    public OrderCommandInterceptor(ExternalServicesManagement externalServicesManagement) {
        this.externalServicesManagement = externalServicesManagement;
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, commandMessage) -> {
            CreateOrderCommand createOrderCommand = (CreateOrderCommand) commandMessage.getPayload();

            Long customerId = externalServicesManagement.getCustomerById(Long.valueOf(createOrderCommand.getCustomerId()));

            if (customerId == null || !customerId.equals(Long.valueOf(createOrderCommand.getCustomerId())))
                throw new ResourceNotFoundException("Customer with id " + createOrderCommand.getCustomerId() + " not found");

            return commandMessage;
        };
    }
}
