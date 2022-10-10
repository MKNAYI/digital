package com.digital.purchaserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients("com.digital")
public class PurchaserServiceApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(PurchaserServiceApplicationTest.class, args);
    }
}
