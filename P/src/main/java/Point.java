import java.sql.SQLOutput;

public class Point {
    double a;
    double b;
    public Point(double a, double b) {
        this.a=a;
        this.b=b;
    }
    public static void main(String[] args) {

        Point p1 = new Point(1, 6);
        Point p2 = new Point(3, 9);

        System.out.println("Расстояние между точками = " + distance(p1,p2));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.a - p2.a)*(p1.a - p2.a) + (p1.b - p2.b)*(p1.b - p2.b));
    }

}



