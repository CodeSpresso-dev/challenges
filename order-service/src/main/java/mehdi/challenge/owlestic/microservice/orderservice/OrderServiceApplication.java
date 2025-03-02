package mehdi.challenge.owlestic.microservice.orderservice;

import mehdi.challenge.owlestic.microservice.orderservice.command.interceptor.OrderCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Autowired
    public void registerCreateOrderCommandInterceptor(ApplicationContext context, CommandBus commandBus){
        commandBus.registerDispatchInterceptor(context.getBean(OrderCommandInterceptor.class));
    }
}
