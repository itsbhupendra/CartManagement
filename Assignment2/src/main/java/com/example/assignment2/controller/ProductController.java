package com.example.assignment2.controller;

import com.example.assignment2.model.Product;
import com.example.assignment2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> showProducts(){
        return productService.showProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @GetMapping(path="{productType}")
    public List<Product> getProductByType(@PathVariable ("productType") String productType){
        return productService.getProductByType(productType);
    }
}
