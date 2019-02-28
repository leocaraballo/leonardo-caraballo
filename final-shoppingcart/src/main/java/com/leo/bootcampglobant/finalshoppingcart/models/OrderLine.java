package com.leo.bootcampglobant.finalshoppingcart.models;

import javax.persistence.Entity;

@Entity
public class OrderLine extends ItemDetail {

  public OrderLine(Product product, int quantity) {
    super(product, quantity);
  }

  public OrderLine() {
  }

  @Override
  public String toString() {
    return "OrderLine{" +
        "id=" + this.getId() +
        ", product=" + this.getProduct() +
        ", quantity=" + this.getQuantity() +
        '}';
  }
}
