package mehdi.challenge.owlestic.microservice.orderservice.core.data;

import jakarta.persistence.*;
import lombok.Data;
import mehdi.challenge.owlestic.microservice.orderservice.core.model.OrderStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    public String orderId;
    private String customerId;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
