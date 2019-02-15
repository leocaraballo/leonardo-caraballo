package com.leo.bootcampglobant.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderLine implements WithId {

  private long id;
  private final Product product;
  private final int quantity;

  public OrderLine(long id, Product product, int quantity) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
  }

  public OrderLine(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public BigDecimal getCost() {
    return product.getPrice().multiply(new BigDecimal(quantity));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderLine orderLine = (OrderLine) o;
    return Objects.equals(product, orderLine.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product);
  }

  @Override
  public String toString() {
    return "OrderLine{" +
        "id=" + id +
        ", product=" + product +
        ", quantity=" + quantity +
        '}';
  }
}
