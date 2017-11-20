package com.nagarro.amcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages="com.nagarro")
@EnableJpaRepositories(basePackages = "com.nagarro.amcart.daos")
@EntityScan(basePackages="com.nagarro.amcart.models")
@ImportResource({ "classpath:platform-config.xml", "classpath:facades-config.xml" })
@EnableElasticsearchRepositories(basePackages = "com.nagarro.amcart.search")
public class App // NOSONAR
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // NOSONAR
    }
}
