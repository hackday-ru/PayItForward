package com.epamhackday.payitforward;

/**
 * Created by bu3apd on 11/14/2014.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
