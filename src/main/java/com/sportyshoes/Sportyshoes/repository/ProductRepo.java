package com.sportyshoes.Sportyshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.Sportyshoes.entity.Products;

public interface ProductRepo extends JpaRepository<Products, Long> {

}
