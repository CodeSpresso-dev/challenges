package mehdi.challenge.owlestic.microservice.orderservice.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ExternalServicesManagement {
    private final CustomerClient customerClient;
    private final ObjectMapper objectMapper;

    public ExternalServicesManagement(CustomerClient customerClient, ObjectMapper objectMapper) {
        this.customerClient = customerClient;
        this.objectMapper = objectMapper;
    }

    public Long getCustomerById(Long id) {
        String customer = customerClient.getCustomerById(id);

        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(customer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Extract customerId from JSON
        Long customerId = rootNode.path("data").path("customerId").asLong();

        return customerId;
    }
}
