package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("Inga");
    hello("user");

    double len = 6;
    System.out.println("Площадь квадрата со стороной " + len + " равна " + area(len));

    double a = 3;
    double b = 5;
    System.out.println("Площадь прямоугоульника со сторонами " + a + " и " + b + " равна " + area(a, b));

  }

  public static void hello (String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area (double l) {
    return l*l;
  }

  public static double area (double a, double b) {
    return a*b;
  }


}