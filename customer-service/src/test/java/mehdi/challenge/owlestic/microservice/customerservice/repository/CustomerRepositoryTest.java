package mehdi.challenge.owlestic.microservice.customerservice.repository;

import mehdi.challenge.owlestic.microservice.customerservice.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer_whenValidCustomerDetailsProvided_shouldReturnStoredCustomer() {
        //arrange
        Customer newCustomer = Customer.builder()
                .name("testName")
                .email("testEmail")
                .build();

        //act
        Customer savedCustomer = customerRepository.save(newCustomer);

        //assert
        assertNotNull(savedCustomer);
        Assertions.assertTrue(savedCustomer.getCustomerId() > 0);
        assertEquals("testName", savedCustomer.getName());
        assertEquals("testEmail", savedCustomer.getEmail());
    }

    @Test
    public void testCreateCustomer_whenNameIsTooLong_shouldThrowException() {
        //arrange
        Customer newCustomer = Customer.builder()
                .name("testNametestNametestNametestNametestNametestNametestNametestNametestNametestNametestNametestNametestNametestName")
                .email("testEmail")
                .build();

        //act & assert
        Assertions.assertThrows(DataIntegrityViolationException.class, () ->
        {
            customerRepository.save(newCustomer);
        }, "was Expecting DataIntegrityViolationException to be thrown");
    }

    @Test
    public void testCreateCustomer_whenExistingEmailProvided_shouldThrowException() {
        //arrange
        // create a customer and save it
        Customer firstCustomer = Customer.builder()
                .name("name1")
                .email("testEmail")
                .build();
        customerRepository.save(firstCustomer);

        // create a second customer with the same email
        Customer secondCustomer = Customer.builder()
                .name("name2")
                .email("testEmail")
                .build();

        //act & assert
        Assertions.assertThrows(DataIntegrityViolationException.class, () ->
        {
            customerRepository.save(secondCustomer);
        }, "was Expecting DataIntegrityViolationException to be thrown");
    }

    @Test
    public void testCreateCustomer_whenNullNameAndEmailProvided_shouldThrowException() {
        //arrange
        Customer newCustomer = Customer.builder().build();

        //act & assert
        Assertions.assertThrows(DataIntegrityViolationException.class, () ->
        {
            customerRepository.save(newCustomer);
        }, "was Expecting DataIntegrityViolationException to be thrown");
    }
}
