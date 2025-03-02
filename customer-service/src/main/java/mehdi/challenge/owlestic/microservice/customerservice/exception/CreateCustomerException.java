package mehdi.challenge.owlestic.microservice.customerservice.exception;

public class CreateCustomerException extends RuntimeException{

    public CreateCustomerException(String message) {
        super(message);
    }
    public CreateCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
