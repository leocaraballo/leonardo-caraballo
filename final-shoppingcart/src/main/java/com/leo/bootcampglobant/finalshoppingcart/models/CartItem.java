package com.leo.bootcampglobant.finalshoppingcart.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class CartItem extends ItemDetail {

  public CartItem(Product product, int quantity) {
    super(product, quantity);
  }

  public CartItem(Long id, Product product,
      @Positive @NotNull int quantity) {
    super(id, product, quantity);
  }

  public CartItem() {
  }

  public OrderLine toOrderLine() {
    return new OrderLine(this.getProduct(), this.getQuantity());
  }

  @Override
  public String toString() {
    return "CartItem{" +
        "id=" + this.getId() +
        ", product=" + this.getProduct() +
        ", quantity=" + this.getQuantity() +
        '}';
  }
}
