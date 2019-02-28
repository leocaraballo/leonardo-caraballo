package com.leo.bootcampglobant.finalshoppingcart.models;

import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class OrderLine extends ItemDetail {

  private BigDecimal cost;

  public OrderLine(Product product, int quantity) {
    super(product, quantity);
    this.cost = product.getFinalPrice().multiply(new BigDecimal(quantity));
  }

  public OrderLine() {
  }

  @Override
  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal originalCost) {
    this.cost = originalCost;
  }

  @Override
  public String toString() {
    return "OrderLine{" +
        "id=" + this.getId() +
        ", product=" + this.getProduct() +
        ", quantity=" + this.getQuantity() +
        ", originalCost=" + this.getCost() +
        '}';
  }
}
