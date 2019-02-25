package com.leo.bootcampglobant.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart {

  @Id
  private Long id;
  @OneToMany
  @JoinColumn(name = "orderline_id")
  private List<OrderLine> currentItems = new ArrayList<>();
  @OneToOne
  @MapsId
  private User user;

  public ShoppingCart(Long id, List<OrderLine> currentItems, User user) {
    this.id = id;
    this.currentItems = currentItems;
    this.user = user;
  }

  public ShoppingCart(List<OrderLine> currentItems, User user) {
    this.currentItems = currentItems;
    this.user = user;
  }

  public ShoppingCart(User user) {
    this.user = user;
  }

  public ShoppingCart() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<OrderLine> getCurrentItems() {
    return currentItems;
  }

  public void setCurrentItems(List<OrderLine> currentItems) {
    this.currentItems = currentItems;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BigDecimal getCurrentCost() {
    return getCurrentItems().stream().map(OrderLine::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public Order produceOrder() {
    return new Order(List.copyOf(this.getCurrentItems()), LocalDateTime.now());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShoppingCart that = (ShoppingCart) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(currentItems, that.currentItems) &&
        Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, currentItems, user);
  }

  @Override
  public String toString() {
    return "ShoppingCart{" +
        "id=" + id +
        ", currentItems=" + currentItems +
        ", user=" + user +
        '}';
  }
}
