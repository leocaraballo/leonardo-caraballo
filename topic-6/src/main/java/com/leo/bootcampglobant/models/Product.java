package com.leo.bootcampglobant.models;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class representing product information.
 */
public class Product implements WithId{

  private long id;
  private String name;
  private String category;
  private BigDecimal price;

  public Product(long id, String name, String category, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.price = price;
  }

  public Product(String name, String category, BigDecimal price) {
    this.name = name;
    this.category = category;
    this.price = price;
  }

  public Product() {

  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id == product.id &&
        Objects.equals(name, product.name) &&
        Objects.equals(category, product.category) &&
        Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, category, price);
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", price=" + price +
        '}';
  }
}
