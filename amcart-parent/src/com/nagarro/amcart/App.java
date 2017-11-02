package com.nagarro.amcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nagarro.amcart.daos")
@EntityScan
@ImportResource({ "classpath:platform-config.xml", "classpath:facades-config.xml" })
public class App // NOSONAR
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // NOSONAR
    }
}
