package com.leo.bootcampglobant.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class OrderLine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
  private int quantity;

  public OrderLine(Long id, Product product, int quantity) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
  }

  public OrderLine(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public OrderLine() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
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
    return quantity == orderLine.quantity &&
        Objects.equals(id, orderLine.id) &&
        Objects.equals(product, orderLine.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, product, quantity);
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
