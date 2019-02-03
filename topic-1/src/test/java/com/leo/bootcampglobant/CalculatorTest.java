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
    assertEquals(calculator.getOperandA(), 12.0, DELTA_TEST);

    calculator.setOperandA(54.0);
    assertEquals(calculator.getOperandA(), 54.0, DELTA_TEST);
  }

  @Ignore("Ignore getter/setter test for operandB")
  @Test
  public void getSetOperandB() {
    Calculator calculator = new Calculator(0.0, 36.0);
    assertEquals(calculator.getOperandB(), 36.0, DELTA_TEST);

    calculator.setOperandB(99.0);
    assertEquals(calculator.getOperandB(), 99.0, DELTA_TEST);
  }

  @Test
  public void sum() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(14.0);
    calculator.setOperandB(87.0);

    assertEquals(calculator.sum(), 101.0, DELTA_TEST);
  }

  @Test
  public void substract() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(100.0);
    calculator.setOperandB(50.0);

    assertEquals(calculator.substract(), 50.0, DELTA_TEST);
  }

  @Test
  public void multiply() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(59.0);
    calculator.setOperandB(37.0);

    assertEquals(calculator.multiply(), 2183.0, DELTA_TEST);
  }

  @Test
  public void divide() {
    Calculator calculator = new Calculator();
    calculator.setOperandA(140.0);
    calculator.setOperandB(2.0);

    assertEquals(calculator.divide(), 70.0, DELTA_TEST);
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