package com.nagarro.amcart.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.nagarro.amcart.search")
public class ElasticsearchConfiguration {
	@Resource
	private Environment environment;

	/*@Bean
	public Client client() {
		TransportClient client = new TransportClient.Builder().build()
		TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"),
				Integer.parseInt(environment.getProperty("elasticsearch.port")));
		client.addTransportAddress(address);
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}*/
}