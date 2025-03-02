package mehdi.challenge.owlestic.microservice.orderservice.command.rest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderCreateRest {
    @NotBlank(message = "Customer Id is required")
    private String customerId;
}
