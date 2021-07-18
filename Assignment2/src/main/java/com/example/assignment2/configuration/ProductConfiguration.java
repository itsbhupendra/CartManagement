package com.example.assignment2.configuration;

import com.example.assignment2.model.Product;
import com.example.assignment2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            Product laptop=new Product(
                    1L,
                    "Laptop",
                    "Electronics",
                    20
            );
            Product swift=new Product(
                    2L,
                    "Swift",
                    "Vehicle",
                    40
            );
            productRepository.saveAll(
                    List.of(laptop,swift)
            );
        };
    }
}
