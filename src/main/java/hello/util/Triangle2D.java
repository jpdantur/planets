package hello.util;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Triangle2D {
    private Point2D p1;
    private Point2D p2;
    private Point2D p3;
    private static final double EPSILON = 0.000001;

    public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public double getArea() {
        return Math.abs((p1.getX() * (p2.getY() - p3.getY()) +
                p2.getX()*(p3.getY() - p1.getY()) +
                p3.getX()*(p1.getY() - p2.getY())) / 2.0);
    }

    public boolean isAligned() {
        return getArea() < EPSILON;
    }

    public boolean contains(Point2D p) {
        Path2D path = new Path2D.Double();
        path.moveTo(p1.getX(),p1.getY());
        path.lineTo(p2.getX(),p2.getY());
        path.lineTo(p3.getX(),p3.getY());
        path.closePath();
        return path.contains(p);
    }
}
