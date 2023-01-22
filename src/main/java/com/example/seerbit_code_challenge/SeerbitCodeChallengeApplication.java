package com.example.seerbit_code_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SeerbitCodeChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeerbitCodeChallengeApplication.class, args);
    }

}
