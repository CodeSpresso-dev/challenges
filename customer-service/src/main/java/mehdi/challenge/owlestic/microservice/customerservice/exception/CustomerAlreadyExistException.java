package mehdi.challenge.owlestic.microservice.customerservice.exception;

public class CustomerAlreadyExistException extends CreateCustomerException{
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
