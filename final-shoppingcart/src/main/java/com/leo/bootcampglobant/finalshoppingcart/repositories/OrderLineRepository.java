package com.leo.bootcampglobant.finalshoppingcart.repositories;

import com.leo.bootcampglobant.finalshoppingcart.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
