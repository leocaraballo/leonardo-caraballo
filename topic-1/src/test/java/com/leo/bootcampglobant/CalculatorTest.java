package com.leo.bootcampglobant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {
  private static final double DELTA_TEST = 0.0;

  @Ignore("Ignore getter/setter test for operandA")
  @Test
  public void getSetOperandA() {
    Calculator calculator = new Calculator(12.0, 0.0);
    assertEquals(12.0, calculator.getOperandA(), DELTA_TEST);

    calculator.setOperandA(54.0);
    assertEquals(54.0, calculator.getOperandA(), DELTA_TEST);
  }

  @Ignore("Ignore getter/setter test for operandB")
  @Test
  public void getSetOperandB() {
    Calculator calculator = new Calculator(0.0, 36.0);
    assertEquals(36.0, calculator.getOperandB(), DELTA_TEST);

    calculator.setOperandB(99.0);
    assertEquals(99.0, calculator.getOperandB(), DELTA_TEST);
  }

  @Test
  public void sum() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(14.0);
    calculator.setOperandB(87.0);

    assertEquals(14.0 + 87.0, calculator.sum(), DELTA_TEST);
  }

  @Test
  public void substract() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(100.0);
    calculator.setOperandB(50.0);

    assertEquals( 100.0 - 50.0, calculator.substract(), DELTA_TEST);
  }

  @Test
  public void multiply() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(59.0);
    calculator.setOperandB(37.0);

    assertEquals(59.0 * 37.0, calculator.multiply(), DELTA_TEST);
  }

  @Test
  public void divide() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(140.0);
    calculator.setOperandB(2.0);

    assertEquals(140.0 / 2.0, calculator.divide(), DELTA_TEST);
  }

  @Test
  public void divide_divisionByZero() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(28.0);
    calculator.setOperandB(0.0);

    try {
      calculator.divide();
      fail();
    } catch (ArithmeticException ex) {
      assertTrue(true);
    }
  }


}