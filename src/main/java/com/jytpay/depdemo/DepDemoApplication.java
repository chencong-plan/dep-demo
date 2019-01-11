package com.jytpay.depdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author chencong@jytpay.com
 */
@SpringBootApplication
public class DepDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepDemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
