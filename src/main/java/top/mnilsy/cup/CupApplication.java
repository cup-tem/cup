package top.mnilsy.cup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class CupApplication {
    public static void main(String[] args) {
        SpringApplication.run(CupApplication.class, args);
    }
}
