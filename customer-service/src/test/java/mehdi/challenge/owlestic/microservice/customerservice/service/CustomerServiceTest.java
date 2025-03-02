package mehdi.challenge.owlestic.microservice.customerservice.service;

import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerRequestDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.entity.Customer;
import mehdi.challenge.owlestic.microservice.customerservice.repository.CustomerRepository;
import mehdi.challenge.owlestic.microservice.customerservice.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @MockBean
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }


    @DisplayName("Customer object created")
    @Test
    public void testCreateCustomer_whenValidCustomerDetailsProvided_shouldReturnCustomerDtoObject() {

        //arrange
        CustomerRequestDto customerRequestDto = new CustomerRequestDto( "John", "John@gmail.com");
        Customer savedCustomer = new Customer(1L, customerRequestDto.name(), customerRequestDto.email());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //act
        CustomerResponseDto result = customerService.createCustomer(customerRequestDto);

        //assert
        assertNotNull(result, "the createCustomer() result should not have null");
        assertTrue(result.customerId() > 0, "Customer's id is incorrect.");
        assertEquals("John", result.name(), "Customer's name is incorrect.");
        assertEquals("John@gmail.com", result.email(), "Customer's email is incorrect.");
    }
}
