package ru.point.pft.addressbook;

public class P {

  public static void main(String[] args) {

    Point p1 = new Point(1, 6);
    Point p2 = new Point(4, 10);

    System.out.println("Расстояние между точками = " + p1.distance(p2));

  }

}