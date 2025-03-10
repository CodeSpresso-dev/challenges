package mehdi.challenge.owlestic.microservice.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "API Response",
        description = "Standardized Schema to hold all apis response information"
)
@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    @Schema(
            description = "Status representing the status of the api response"
    )
    private String statusCode;

    @Schema(
            description = "Message representing the result of the api response"
    )
    private String statusMessage;

    @Schema(
            description = "A general object to hold the data of the api response",
            implementation = CustomerResponseDto.class
    )
    private T data;
}
