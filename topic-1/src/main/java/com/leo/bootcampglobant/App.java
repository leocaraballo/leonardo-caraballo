package com.leo.bootcampglobant;

public class App {

  public static void main(String[] args) {
    Calculator calculator = new Calculator();

    calculator.setOperandA(24.0);
    calculator.setOperandB(2.0);

    System.out.println(calculator.sum());
    System.out.println(calculator.substract());
    System.out.println(calculator.multiply());
    System.out.println(calculator.divide());
  }
}
