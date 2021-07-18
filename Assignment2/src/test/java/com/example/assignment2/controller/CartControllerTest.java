package com.example.assignment2.controller;

import com.example.assignment2.model.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import com.example.assignment2.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void showCartProduct() throws Exception {
        mockMvc.perform(get("/api/v1/cart/1")).andExpect(status().is4xxClientError());
    }

    @Test
    void addProductToCart() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Cart cart=new Cart();
        cart.setCartId(1L);
        List<Product> productList=new ArrayList<>();
        Product product=new Product(1L,"laptop","electronics",200);
        productList.add(product);
        cart.setProductList(productList);
        String cartTest=objectMapper.writeValueAsString(cart);

        mockMvc.perform(put("/api/v1/cart/1/2").content(cartTest)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    void productList() throws Exception {
        mockMvc.perform(get("/api/v1/cart/show/1")).andExpect(status().is4xxClientError());
    }
    @Test
    void EmptyCartTest() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Cart cart=new Cart();
        cart.setCartId(1L);
        List<Product> productList=new ArrayList<>();
        Product product=new Product(1L,"laptop","electronics",200);
        productList.add(product);
        cart.setProductList(productList);

        String cartTest=objectMapper.writeValueAsString(cart);
        mockMvc.perform(put("/api/v1/cart/Empty_Cart/1")
                .content(cartTest).header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    void DeleteProductFromCartTest() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Cart cart=new Cart();
        cart.setCartId(1L);
        List<Product> productList=new ArrayList<>();
        Product product=new Product(1L,"laptop","electronics",200);
        productList.add(product);
        cart.setProductList(productList);
        String cartTest=objectMapper.writeValueAsString(cart);
        mockMvc.perform(put("/api/v1/cart/Delete_Product/1/1")
                .content(cartTest).header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}