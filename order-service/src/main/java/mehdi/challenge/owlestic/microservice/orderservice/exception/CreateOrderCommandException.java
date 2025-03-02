package mehdi.challenge.owlestic.microservice.orderservice.exception;

public class CreateOrderCommandException extends RuntimeException{

    public CreateOrderCommandException(String message) {
        super(message);
    }
    public CreateOrderCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
