package mehdi.challenge.owlestic.microservice.customerservice.service.impl;

import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerRequestDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.entity.Customer;
import mehdi.challenge.owlestic.microservice.customerservice.exception.CustomerAlreadyExistException;
import mehdi.challenge.owlestic.microservice.customerservice.repository.CustomerRepository;
import mehdi.challenge.owlestic.microservice.customerservice.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {

        CustomerResponseDto existingUser = findByEmailAddress(customerRequestDto.email());
        if (existingUser != null) {
            throw new CustomerAlreadyExistException("Customer with email address " + existingUser.email() + " already exist.");
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDto, customer);

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponseDto(savedCustomer.getCustomerId(),
                savedCustomer.getName(), savedCustomer.getEmail());
    }

    @Override
    public CustomerResponseDto findByEmailAddress(String emailAddress) {
        return customerRepository.findByEmail(emailAddress)
                .map(customer -> new CustomerResponseDto(customer.getCustomerId(), customer.getName(), customer.getEmail()))
                .orElseGet(() -> null);
    }
}
