package com.leo.bootcampglobant.finalshoppingcart.models;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@MappedSuperclass
public class ItemDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
  @Positive
  @NotNull
  private int quantity;

  public ItemDetail(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public ItemDetail() {
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
    ItemDetail that = (ItemDetail) o;
    return quantity == that.quantity &&
        id.equals(that.id) &&
        product.equals(that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, product, quantity);
  }

  @Override
  public String toString() {
    return "ItemDetail{" +
        "id=" + id +
        ", product=" + product +
        ", quantity=" + quantity +
        '}';
  }
}
