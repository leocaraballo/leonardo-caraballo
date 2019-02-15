package com.leo.bootcampglobant.repositories;

import com.leo.bootcampglobant.models.OrderLine;
import java.util.List;

public interface OrderLineRepository extends Repository<OrderLine, Long> {
  void clear();
}
