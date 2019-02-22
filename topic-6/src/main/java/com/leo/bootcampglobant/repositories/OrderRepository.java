package com.leo.bootcampglobant.repositories;

import com.leo.bootcampglobant.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
