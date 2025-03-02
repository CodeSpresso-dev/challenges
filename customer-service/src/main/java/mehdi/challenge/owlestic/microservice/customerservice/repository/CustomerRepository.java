package mehdi.challenge.owlestic.microservice.customerservice.repository;

import mehdi.challenge.owlestic.microservice.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
