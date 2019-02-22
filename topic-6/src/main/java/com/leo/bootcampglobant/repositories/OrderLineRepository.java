package com.leo.bootcampglobant.repositories;

import com.leo.bootcampglobant.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
