package mehdi.challenge.owlestic.microservice.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Customer Response",
        description = "Schema to hold and display customer information"
)
public record CustomerResponseDto(
        @Schema(
                description = "Customer ID",
                example = "1"
        )
        Long customerId,
        @Schema(
                description = "Customer Name",
                example = "Mehdi"
        )
        String name,
        @Schema(
                description = "Customer Email",
                example = "test@example.com"
        )
        String email) {
}
