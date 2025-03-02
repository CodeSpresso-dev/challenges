package mehdi.challenge.owlestic.microservice.customerservice.service;

import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerRequestDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerResponseDto;

import java.util.Optional;

public interface CustomerService {

    /**
     * @param customerRequestDto
     * @return
     */
    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    /**
     * @param emailAddress
     * @return
     */
    CustomerResponseDto findByEmailAddress(String emailAddress);
}
