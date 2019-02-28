package com.leo.bootcampglobant.finalshoppingcart.repositories;

import com.leo.bootcampglobant.finalshoppingcart.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
