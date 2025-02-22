package org.example;

public class App {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    //System.out.println(new App().getGreeting());
    ShuntingYard shuntingYard = new ShuntingYard();

    String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
    double result = shuntingYard.evaluate(infix);
    System.out.println(result);
  }
}
