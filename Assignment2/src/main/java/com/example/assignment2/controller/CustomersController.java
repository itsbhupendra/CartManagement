package com.example.assignment2.controller;

import com.example.assignment2.model.Customers;
import com.example.assignment2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/customer")
public class CustomersController {

    private final CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void initialiseCustomer(@RequestBody Customers customer){
        customerService.initialiseCustomer(customer);
    }
    @GetMapping
    public List<Customers> getCustomer(){
        return customerService.getCustomers();
    }
    @GetMapping(path="{userId}")
    public String getCustomer(@PathVariable ("userId") Long userId){
        return customerService.getCustomer(userId);
    }

}
