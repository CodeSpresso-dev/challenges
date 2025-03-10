package mehdi.challenge.owlestic.microservice.customerservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Customers microservice REST API Documentation",
				description = "My_Challenge microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Mahdi Shivaeifar",
						email = "Shivaeifar.m@gmail.com",
						url = "https://github.com/CodeSpresso-dev"
				),
				license = @License(
						name = "This is a sample project and is created to confirm the final step of my job opportunity in Owlestic Co.",
						url = "https://github.com/CodeSpresso-dev/challenges"
				)
		)
)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
