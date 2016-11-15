package com.raaldi.banker.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.raaldi.banker.authentication", "com.raaldi.banker.authentication.controller",
    "com.raaldi.banker.authentication.service", "com.raaldi.banker.authentication.configuration",
    "com.raaldi.banker.authentication.token" })
@EnableJpaRepositories(basePackages = { "com.raaldi.banker.sale.repository",
    "com.raaldi.banker.authentication.repository" })
@EntityScan(basePackages = { "com.raaldi.banker.sale.model", "com.raaldi.banker.authentication.model" })
@EnableTransactionManagement
@SpringBootApplication
public class BankerSaleApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankerSaleApplication.class, args);
  }
}