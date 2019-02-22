package com.leo.bootcampglobant.repositories;

import com.leo.bootcampglobant.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
