package com.zzmr.fgback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FgBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FgBackApplication.class, args);
    }

}
