package com.leo.bootcampglobant.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany
  @JoinColumn(name = "orderline_id")
  private List<OrderLine> items;
  private LocalDateTime timestamp;

  public Order(Long id, List<OrderLine> items, LocalDateTime timestamp) {
    this.id = id;
    this.items = items;
    this.timestamp = timestamp;
  }

  public Order(List<OrderLine> items, LocalDateTime timestamp) {
    this.items = items;
    this.timestamp = timestamp;
  }

  public Order() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<OrderLine> getItems() {
    return items;
  }

  public void setItems(List<OrderLine> items) {
    this.items = items;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public BigDecimal getTotalCost() {
    return items.stream().map(OrderLine::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(id, order.id) &&
        Objects.equals(items, order.items) &&
        Objects.equals(timestamp, order.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, items, timestamp);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", items=" + items +
        ", timestamp=" + timestamp +
        '}';
  }

}
