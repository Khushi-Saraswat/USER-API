package com.pro.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.demo.Model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

   // public Product findById(Integer id);

}
