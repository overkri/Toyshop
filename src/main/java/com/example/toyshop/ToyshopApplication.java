package com.example.toyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.example.toyshop.elasticSearchRepository")
@EnableJpaRepositories(basePackages = {"com.example.toyshop.repository"})
public class ToyshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyshopApplication.class, args);
    }

}
