package mehdi.challenge.owlestic.microservice.orderservice.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
public record ApiErrorResponseDto(
        String apiPath,

        HttpStatus errorCode,

        String errorMessage,

        LocalDateTime errorTime) {
}
