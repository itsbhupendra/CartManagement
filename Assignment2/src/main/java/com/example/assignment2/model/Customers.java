package com.example.assignment2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @OneToOne
            (cascade = CascadeType.ALL)
//            (mappedBy = "users")
    private Cart cart;

    public Customers(String userName, Cart cart) {
        this.userName = userName;
        this.cart = cart;
    }

    public Customers(String userName) {
        this.userName = userName;
        this.cart=new Cart(this.getUserId(),new ArrayList<>());
    }

    public void setCart(Cart cart) {
        this.cart = new Cart(this.getUserId(),new ArrayList<>());
    }

    public Cart getCart() {
        return new Cart(this.getUserId(),new ArrayList<>());
    }

    public Long getUserId() {
        return this.userId;
    }

//    @Override
//    public String toString() {
//        return "Customers{" +
//                "userId=" + userId +
//                ", userName='" + userName + '\'' +
//                ", cart=" + cart +
//                '}';
//    }
    //    @Override
//    public String toString() {
//        return "Customers{" +
//                "userId=" + userId +
//                ", userName='" + userName + '\'' +
//                ", cart=" + cart.getProductList()+ cart.getCartId()+
//                '}';
//    }
}
