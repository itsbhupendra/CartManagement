package com.example.assignment2.service;

import com.example.assignment2.exception.ApiRequestException;
import com.example.assignment2.model.Cart;
import com.example.assignment2.model.Product;
import com.example.assignment2.repository.CartRepository;
import com.example.assignment2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<Product> showCartProduct(Long cartId) {

        if(!cartRepository.existsById(cartId)){
            throw new ApiRequestException("Cart does not exists!!");
        }
        Cart cart=cartRepository.getById(cartId);
//        return cart.
        if(cart.getProductList().isEmpty()){
            throw new ApiRequestException("Cart is Empty!!");
        }
        return cart.getProductList();
    }

    public Cart addProductToCart(Long cartId, Long productId) {
        if(!cartRepository.existsById(cartId)){
            throw new ApiRequestException("Cart does not exists!!");
        }
        if(!productRepository.existsById(productId)){
            throw new ApiRequestException("Product does not exists");
        }
        Optional<Cart> cartById=cartRepository.findById(cartId);
        Cart cart=cartById.get();
        Optional<Product> productById=productRepository.findById(productId);
        Product product=productById.get();
        List<Product> productList=cart.getProductList();
        if(productList.contains(product)){
            throw new ApiRequestException("Product already present in cart");
        }
        productList.add(product);
        cart.setProductList(productList);
//        cart.getProductList().add(product);
        Cart newCart= new  Cart(cartId,productList);
        cartRepository.save(newCart);
        return newCart;
    }

    public List<Product> showProductList(Long cartId) {
        if(!cartRepository.existsById(cartId)){
            throw new ApiRequestException("Cart does not exists!!");
        }
        Optional<Cart> cartById=cartRepository.findById(cartId);
        Cart cart=cartById.get();
        return cart.getProductList();
    }

    public void emptyCart(Long cartId) {
        if(!cartRepository.existsById(cartId)){
            throw new ApiRequestException("Cart with cart Id: "+cartId+" does not exists");
        }
        Cart cart=cartRepository.findById(cartId).get();
        cart.setProductList(new ArrayList<>());
        cartRepository.save(cart);
    }

    public void deleteProductFromCart(Long cartId, Long productId) {
        if(!cartRepository.existsById(cartId)){
            throw new ApiRequestException("cart with cart Id: "+cartId+" does not exists");
        }
        Cart cart=cartRepository.findById(cartId).get();
        List<Product> productList=cart.getProductList();
        Product product=productRepository.findById(productId).get();
        if(!productList.contains(product)){
            throw new ApiRequestException("Product with product Id: "+productId+" does not exists int cart with cart id: "+cartId);
        }
        productList.remove(product);
        cart.setProductList(productList);
        cartRepository.save(cart);
    }
}
