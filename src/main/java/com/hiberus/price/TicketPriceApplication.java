package com.hiberus.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.hiberus.price.model")
public class TicketPriceApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TicketPriceApplication.class, args);
    }
}
