package mehdi.challenge.owlestic.microservice.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Customers",
        description = "Schema to hold new customer information to register"
)
public record CustomerRequestDto(
        @Schema(
                description = "Customer's name"
        )
        String name,
        @Schema(
                description = "Customer's email",
                example = "test@gmail.com"
        )
        String email) {
}
