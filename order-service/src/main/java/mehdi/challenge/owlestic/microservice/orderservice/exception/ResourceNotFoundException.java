package mehdi.challenge.owlestic.microservice.orderservice.exception;

public class ResourceNotFoundException extends CreateOrderCommandException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
