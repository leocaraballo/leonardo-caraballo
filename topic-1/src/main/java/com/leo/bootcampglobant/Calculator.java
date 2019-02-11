package com.leo.bootcampglobant;

public class Calculator {
  private Double operandA = null;
  private Double operandB = null;

  public Calculator() {
  }

  public Calculator(double operandA, double operandB) {
    this.operandA = operandA;
    this.operandB = operandB;
  }

  public double getOperandA() {
    return operandA;
  }

  public void setOperandA(double operandA) {
    this.operandA = operandA;
  }

  public double getOperandB() {
    return operandB;
  }

  public void setOperandB(double operandB) {
    this.operandB = operandB;
  }

  public double sum() throws OperandNotSetException {
    checkOperands();
    return operandA + operandB;
  }

  public double substract() throws OperandNotSetException {
    checkOperands();
    return operandA - operandB;
  }

  public double multiply() throws OperandNotSetException {
    checkOperands();
    return operandA * operandB;
  }

  public double divide() throws OperandNotSetException {
    checkOperands();
    if (operandB == 0.0) {
      throw new ArithmeticException("Division by zero");
    }
    return operandA / operandB;
  }

  private void checkOperands() throws OperandNotSetException {
    if (operandA == null) {
      throw new OperandNotSetException("Attempt to operate on operandA before setting it.");
    }
    if (operandB == null) {
      throw new OperandNotSetException("Attempt to operate on operandB before setting it.");
    }
  }
}
