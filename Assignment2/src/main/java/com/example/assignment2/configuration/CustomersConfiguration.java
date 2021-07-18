package com.example.assignment2.configuration;

import com.example.assignment2.model.Cart;
import com.example.assignment2.model.Customers;
import com.example.assignment2.repository.CustomersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomersConfiguration {
    @Bean
    CommandLineRunner commandLineRunnerSecond(CustomersRepository customersRepository){
       return args -> {
           Customers customer1=new Customers(1L,"Bhupendra Singh",null);
           customersRepository.saveAll(
                   List.of(customer1)
           );
       };
    }
}
