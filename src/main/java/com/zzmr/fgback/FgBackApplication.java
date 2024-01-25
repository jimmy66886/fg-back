package com.zzmr.fgback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class FgBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FgBackApplication.class, args);
    }

}
