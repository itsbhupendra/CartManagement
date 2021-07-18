package com.example.assignment2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.assignment2.model.Customers;
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


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class CustomersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void initialiseCustomer() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        Customers testCustomer=new Customers("Bhupendra");
//        Cart cart;
        testCustomer.setCart(testCustomer.getCart());
        String customer=objectMapper.writeValueAsString(testCustomer);

        mockMvc.perform(post("/api/v1/customer").content(customer)
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void getCustomer() throws Exception{
        mockMvc.perform(get("/api/v1/customer")).andExpect(status().isOk());
    }

    @Test
    void testGetCustomer() throws Exception{
        mockMvc.perform(get("/api/v1/customer/1")).andExpect(status().isOk());

    }
}