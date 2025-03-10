package mehdi.challenge.owlestic.microservice.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
public record ApiErrorResponseDto(
        @Schema(
                description = "API path invoked by client"
        )
        String apiPath,
        @Schema(
                description = "Error code representing the error happened"
        )
        HttpStatus errorCode,
        @Schema(
                description = "Error message representing the error happened"
        )
        String errorMessage,
        @Schema(
                description = "Time representing when the error happened"
        )
        LocalDateTime errorTime) {
}
