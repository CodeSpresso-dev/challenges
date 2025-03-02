package mehdi.challenge.owlestic.microservice.customerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mehdi.challenge.owlestic.microservice.customerservice.constants.CustomerConstants;
import mehdi.challenge.owlestic.microservice.customerservice.dto.ApiErrorResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.ApiResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerRequestDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for Customers in the My_Challenge application",
        description = "REST APIs in My_Challenge includes CREATE service"
)
@RestController
@RequestMapping(path = "/api/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Create Customer REST API",
            description = "REST API to create new customer inside My_Challenge application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "HTTP Status CONFLICT",
                    content = @Content(
                            schema = @Schema(implementation = ApiErrorResponseDto.class)
                    )
            )

    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponse = customerService.createCustomer(customerRequestDto);
        ApiResponseDto<CustomerResponseDto> response = new ApiResponseDto<>(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201, customerResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get Customer REST API(Only for testing)",
            description = "REST API to get customer's information inside My_Challenge application"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CustomerResponseDto>> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto customerResponse = new CustomerResponseDto(id, "Test", "Test@Test.com");
        ApiResponseDto<CustomerResponseDto> response = new ApiResponseDto<>(CustomerConstants.STATUS_200, CustomerConstants.MESSAGE_200, customerResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
