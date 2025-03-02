package mehdi.challenge.owlestic.microservice.orderservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8081/api/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    String getCustomerById(@PathVariable Long id);
}
