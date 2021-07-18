package com.example.assignment2.controller;

import com.example.assignment2.model.Cart;
import com.example.assignment2.model.Product;
import com.example.assignment2.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "{cartId}")
    public List<Product> showCartProduct(@PathVariable("cartId") Long cartId){
        return cartService.showCartProduct(cartId);
    }

    @PutMapping (path = "{cartId}/{productId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId){
        return new ResponseEntity<Cart>(cartService.addProductToCart(cartId,productId), HttpStatus.OK);
    }

    @GetMapping(path = "show/{cartId}")
    public List<Product> productList(@PathVariable("cartId") Long cartId){
        return cartService.showProductList(cartId);
    }
    @PutMapping(path = "Empty_Cart/{cartId}")
    public void EmptyCart(@PathVariable("cartId") Long cartId){
        cartService.emptyCart(cartId);
    }

    @PutMapping(path = "Delete_Product/{cartId}/{productId}")
    public void DeleteProductFromCart(@PathVariable("cartId") Long cartId,@PathVariable("productId")Long productId){
        cartService.deleteProductFromCart(cartId,productId);
    }
//    @PostMapping
}
