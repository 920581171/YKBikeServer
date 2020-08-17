package com.yk.bike;

import com.yk.bike.utils.application.EnableApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 92058
 */
@SpringBootApplication
@EnableApplicationContext
public class YKBikeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(YKBikeServerApplication.class, args);
    }
}
