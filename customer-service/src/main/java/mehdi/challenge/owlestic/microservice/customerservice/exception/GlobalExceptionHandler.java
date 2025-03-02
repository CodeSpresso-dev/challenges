package mehdi.challenge.owlestic.microservice.customerservice.exception;

import mehdi.challenge.owlestic.microservice.customerservice.dto.ApiErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponseDto> handleCustomerAlreadyExistException(CustomerAlreadyExistException exception, WebRequest webRequest) {

        ApiErrorResponseDto response = new ApiErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.CONFLICT, exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleOtherExceptions(Exception exception, WebRequest webRequest) {

        ApiErrorResponseDto response = new ApiErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
