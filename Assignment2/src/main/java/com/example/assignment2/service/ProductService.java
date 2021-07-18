package com.example.assignment2.service;

import com.example.assignment2.exception.ApiRequestException;
import com.example.assignment2.model.Product;
import com.example.assignment2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> showProducts(){
        if(productRepository.count()==0){
            throw new ApiRequestException("Oops!! No Product Found");
        }
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        Optional<Product> productName=productRepository.findByName(product.getName());
        if(productName.isPresent()){

            throw new IllegalStateException("Product" +product.getName()+ " already Present");
        }
        productRepository.save(product);
    }

    public List<Product> getProductByType(String productType) {
        List<Product> productListByType= productRepository.findProductByType(productType);
        if(productListByType.isEmpty()){
            throw new ApiRequestException("Oops!! No Product Found");
//            throw new IllegalStateException("No products Found!");
        }
        return productListByType;
    }
}
