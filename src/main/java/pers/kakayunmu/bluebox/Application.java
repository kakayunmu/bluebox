package pers.kakayunmu.bluebox;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
