package com.leo.bootcampglobant.repositories;

import com.leo.bootcampglobant.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
