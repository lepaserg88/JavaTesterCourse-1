package ru.point.pft.addressbook;

public class Point {

    double a;
    double b;
    public Point(double a, double b) {
        this.a=a;
        this.b=b;
    }

    public double distance(Point other) {
        return Math.sqrt((this.a - other.a)*(this.a - other.a) + (this.b - other.b)*(this.b - other.b));
    }

}



