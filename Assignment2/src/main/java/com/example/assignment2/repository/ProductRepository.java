package com.example.assignment2.repository;

import com.example.assignment2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.name=?1")
    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.type=?1")
    List<Product> findProductByType(String type);
}
