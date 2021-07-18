package com.example.assignment2.configuration;

import com.example.assignment2.model.Cart;
import com.example.assignment2.model.Product;
import com.example.assignment2.repository.CartRepository;
import com.example.assignment2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CartConfiguration {

    private final ProductRepository productRepository;

    @Autowired
    public CartConfiguration(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    CommandLineRunner commandLineRunnerThird(CartRepository cartRepository){
        return args -> {
            Cart cart=new Cart();
            cart.setCartId(1L);
            List<Product> productList=new ArrayList<>();
            Product product=new Product(1L,"laptop","electronics",200);
            productRepository.save(product);
            productList.add(product);
            cart.setProductList(productList);
//            Customers customer1=new Customers(1L,"Bhupendra Singh",null);
            cartRepository.saveAll(
                    List.of(cart)
            );
        };
    }
}
