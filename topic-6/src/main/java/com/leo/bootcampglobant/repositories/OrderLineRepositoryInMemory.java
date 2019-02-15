package com.leo.bootcampglobant.repositories;

import com.leo.bootcampglobant.models.OrderLine;
import java.util.ArrayList;
import java.util.List;

public class OrderLineRepositoryInMemory
    extends RepositoryInMemory<OrderLine>
    implements OrderLineRepository {

  @Override
  public void clear() {
    this.entities = new ArrayList<>();
  }

}
