package com.nagarro.amcart.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PersistenceConfiguration {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource primaryDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@FlywayDataSource
	@ConfigurationProperties(prefix="datasource.flyway")
	public DataSource flywayDataSource(){
		return DataSourceBuilder.create().build();
	}

}
