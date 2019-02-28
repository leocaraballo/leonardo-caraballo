package com.leo.bootcampglobant.finalshoppingcart.repositories;

import com.leo.bootcampglobant.finalshoppingcart.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
