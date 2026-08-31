package cl.duoc.eft.reparto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RepartoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepartoApplication.class, args);
    }
}
