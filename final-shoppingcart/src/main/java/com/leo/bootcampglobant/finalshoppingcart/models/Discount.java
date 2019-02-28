package com.leo.bootcampglobant.finalshoppingcart.models;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Positive
  @Max(1)
  @NotNull
  private BigDecimal percentage;
  private String reason;

  public Discount(
      @Positive @Max(1) @NotNull BigDecimal percentage, String reason) {
    this.percentage = percentage;
    this.reason = reason;
  }

  public Discount() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPercentage() {
    return percentage;
  }

  public void setPercentage(BigDecimal percentage) {
    this.percentage = percentage;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Discount discount = (Discount) o;
    return Objects.equals(id, discount.id) &&
        Objects.equals(percentage, discount.percentage) &&
        Objects.equals(reason, discount.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, percentage, reason);
  }

  @Override
  public String toString() {
    return "Discount{" +
        "id=" + id +
        ", percentage=" + percentage +
        ", reason='" + reason + '\'' +
        '}';
  }
}
