package mehdi.challenge.owlestic.microservice.orderservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
