package top.mnilsy.cup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.mnilsy.cup.utils.SpringUtil;

@EnableTransactionManagement
@SpringBootApplication
public class CupApplication {

    @Bean
    public SpringUtil getSpingUtil() {
        return new SpringUtil();
    }

    public static void main(String[] args) {
        SpringApplication.run(CupApplication.class, args);
    }
}
