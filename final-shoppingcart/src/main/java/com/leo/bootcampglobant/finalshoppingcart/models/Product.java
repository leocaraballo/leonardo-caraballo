package com.leo.bootcampglobant.finalshoppingcart.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;
  @Size(min = 2, max = 255)
  @Column(unique = true)
  @NotNull
  private String name;
  @Positive
  @NotNull
  private BigDecimal price;
  @ManyToMany
  @JoinTable(name = "product_discount",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "discount_id")
  )
  @JoinColumn(name = "discount_id")
  private List<Discount> discounts = new ArrayList<>();

  public Product(Long id, Category category,
      @Size(min = 2, max = 255) @NotNull String name,
      @Positive @NotNull BigDecimal price) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.price = price;
  }

  public Product(Category category,
      @Size(min = 2, max = 255) @NotNull String name,
      @Positive @NotNull BigDecimal price) {
    this.category = category;
    this.name = name;
    this.price = price;
  }

  public Product(
      @Size(min = 2, max = 255) @NotNull String name,
      @Positive @NotNull BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public Product() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public List<Discount> getDiscounts() {
    return discounts;
  }

  public void setDiscounts(
      List<Discount> discounts) {
    this.discounts = discounts;
  }

  public BigDecimal getFinalPrice() {
    BigDecimal totalDiscount =
        this.discounts.stream().map(Discount::getPercentage)
            .reduce(BigDecimal.ZERO, BigDecimal::add).min(BigDecimal.ONE);

    return this.price.multiply(BigDecimal.ONE.subtract(totalDiscount));
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
    return Objects.equals(id, product.id) &&
        Objects.equals(name, product.name) &&
        Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price);
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        '}';
  }

}
