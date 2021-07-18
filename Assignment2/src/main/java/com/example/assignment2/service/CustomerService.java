package com.example.assignment2.service;

import com.example.assignment2.exception.ApiRequestException;
import com.example.assignment2.model.Cart;
import com.example.assignment2.model.Customers;
import com.example.assignment2.repository.CartRepository;
import com.example.assignment2.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomersRepository customersRepository;
    private final CartRepository cartRepository;
    @Autowired
    public CustomerService(CustomersRepository customersRepository, CartRepository cartRepository) {
        this.customersRepository = customersRepository;
        this.cartRepository = cartRepository;
    }


    public String getCustomer(Long userId) {
        if(!customersRepository.existsById(userId)){
            throw new ApiRequestException("User does not exists!!");
        }
        Optional<Customers> customers=customersRepository.findById(userId);
        return  customers.toString();
//        return customers;
    }

    public List<Customers> getCustomers() {
         if(customersRepository.count()==0){
             throw new ApiRequestException("Oops!! No customers found.");
         }
//        System.out.println("Id: "+customer.getUserName()+customer.getUserId());
        return customersRepository.findAll();
    }

    public void initialiseCustomer(Customers customer) {
//        Optional<Customers> customerById=customersRepository.findById(customer.getUserId());
//        if(customerById.isPresent()){
//            throw new IllegalStateException("Customer: " +customer.getUserName()+ " already Present");
//        }
//        Optional<Cart> cartById=cartRepository.findById(customer.getCart().getCartId());
//        if(cartById.isPresent()){
//            throw  new IllegalStateException("bhu");
//        }
//        System.out.println(customer.toString());
//        System.out.println("Id: "+customer.getUserName()+customer.getUserId());4
        customersRepository.save(customer);
        System.out.println("Id: "+customer.getUserName()+customer.getUserId());
        Cart cart=new Cart(customer.getUserId(),new ArrayList<>());
        cartRepository.save(cart);
//        customersRepository.findById(customer.getUserId());



//        cartRepository.save(customer.getCart());
//        Cart cart=customer.getCart();
//        Optional<Cart> cartById=cartRepository.findById(cart.getCartId());
//        if(cartById.isPresent()){
//            throw new IllegalStateException("Customer" +customer.getUserId()+ " already Present");
//        }
//        cartRepository.save(cart);
//        cart.setCartId(customer.getUserId());

    }
}
