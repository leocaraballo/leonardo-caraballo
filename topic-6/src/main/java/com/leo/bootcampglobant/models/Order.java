package com.leo.bootcampglobant.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a order made by a client checkout.
 */
public class Order implements WithId {

  private long id;
  private final List<OrderLine> orderList;
  private final LocalDateTime timestamp;

  public Order(long id, List<OrderLine> orderList, LocalDateTime timestamp) {
    this.id = id;
    this.orderList = orderList;
    this.timestamp = timestamp;
  }

  public Order(List<OrderLine> orderList, LocalDateTime timestamp) {
    this.orderList = orderList;
    this.timestamp = timestamp;
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public void setId(long id) {
    this.id = id;
  }

  public List<OrderLine> getOrderList() {
    return orderList;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public BigDecimal getTotalCost() {
    return orderList.stream().map(OrderLine::getCost).
        reduce(BigDecimal.ZERO, BigDecimal::add);
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
    return id == order.id &&
        Objects.equals(orderList, order.orderList) &&
        Objects.equals(timestamp, order.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderList, timestamp);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", orderList=" + orderList +
        ", timestamp=" + timestamp +
        '}';
  }
}
