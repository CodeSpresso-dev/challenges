package mehdi.challenge.owlestic.microservice.customerservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mehdi.challenge.owlestic.microservice.customerservice.constants.CustomerConstants;
import mehdi.challenge.owlestic.microservice.customerservice.dto.ApiErrorResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.ApiResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerRequestDto;
import mehdi.challenge.owlestic.microservice.customerservice.dto.CustomerResponseDto;
import mehdi.challenge.owlestic.microservice.customerservice.exception.CustomerAlreadyExistException;
import mehdi.challenge.owlestic.microservice.customerservice.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Customer Object created")
    @Test
    public void testCreateCustomer_whenValidCustomerDetailsProvided_shouldReturnResponseDtoObjectWithCreatedStatus() throws Exception {
        //arrange
        CustomerRequestDto customerRequestDto = new CustomerRequestDto("test", "test@gmail.com");
        CustomerResponseDto customerResponseDto = new CustomerResponseDto(1L, customerRequestDto.name(), customerRequestDto.email());

        when(customerService.createCustomer(any(CustomerRequestDto.class))).thenReturn(customerResponseDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/customers/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequestDto));

        //act
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated()).andReturn();
        String reponseBodyAsString = result.getResponse().getContentAsString();
        ApiResponseDto<CustomerResponseDto> createCustomerResponse = objectMapper.readValue(reponseBodyAsString, new TypeReference<ApiResponseDto<CustomerResponseDto>>(){});

        //assert
        Assertions.assertEquals(CustomerConstants.STATUS_201, createCustomerResponse.getStatusCode(), "Customer's created status is incorrect.");
        Assertions.assertEquals(CustomerConstants.MESSAGE_201, createCustomerResponse.getStatusMessage(), "Customer's created message is incorrect.");
        Assertions.assertEquals(customerResponseDto.customerId(), createCustomerResponse.getData().customerId(), "Customer's id is incorrect.");
        Assertions.assertEquals(customerResponseDto.name(), createCustomerResponse.getData().name(), "Customer's name is incorrect.");
        Assertions.assertEquals(customerResponseDto.email(), createCustomerResponse.getData().email(), "Customer's email is incorrect.");
    }

    @DisplayName("Customer Already Exists Error")
    @Test
    public void testCreateCustomer_whenEmailAlreadyExists_shouldReturnErrorResponseDtoObject() throws Exception {
        //arrange
        CustomerRequestDto customerRequestDto = new CustomerRequestDto("test", "test@gmail.com");
        String errorMessage = "Customer with email address " + customerRequestDto.email() + " already exist.";

        when(customerService.createCustomer(any(CustomerRequestDto.class))).thenThrow(new CustomerAlreadyExistException(errorMessage));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/customers/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequestDto));

        //act
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isConflict()).andReturn();
        String reponseBodyAsString = result.getResponse().getContentAsString();
        ApiErrorResponseDto alreadyExistResponse = objectMapper.readValue(reponseBodyAsString, ApiErrorResponseDto.class);

        //assert
        Assertions.assertEquals(HttpStatus.CONFLICT, alreadyExistResponse.errorCode(), "Customer already exist status is incorrect.");
        Assertions.assertEquals(errorMessage, alreadyExistResponse.errorMessage(), "Customer already exist error message is incorrect.");
        verify(customerService, times(1)).createCustomer(any(CustomerRequestDto.class));
    }
}
