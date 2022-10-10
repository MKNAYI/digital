package com.digital.purchaserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({ "com.digital" })
@EntityScan(value = "com.digital")
@EnableJpaAuditing
@EnableJpaRepositories("com.digital")
@EnableFeignClients("com.digital")
@EnableSwagger2
public class PurchaserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PurchaserServiceApplication.class, args);
    }

    @Bean
    public Docket purchaserApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.digital")).build();
    }
}
