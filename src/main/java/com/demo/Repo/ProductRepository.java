package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
