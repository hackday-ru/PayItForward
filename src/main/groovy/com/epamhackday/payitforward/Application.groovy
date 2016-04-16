package com.epamhackday.payitforward

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by bu3apd on 4/16/2016.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
