package Basis;

import java.util.ArrayList;
/**
 The Basis.Rectangle class represents a rectangle with a location, width, height, and color.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line rib1;
    private Line rib2;
    private Line rib3;
    private Line rib4;

    /**
     Creates a new Basis.Rectangle with a given location, width, height, and color.
     @param upperLeft the upper-left point of the rectangle.
     @param width the width of the rectangle.
     @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        Point start, end;
        start = this.getUpperLeft();
        end = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        this.rib1 = new Line(start, end);
        start = this.getUpperLeft();
        end = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        this.rib2 = new Line(start, end);
        start = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
        end = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        this.rib3 = new Line(start, end);
        start = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        end = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
        this.rib4 = new Line(start, end);
    }
    /**
     Creates a new Basis.Rectangle with a given location, width, height, and color.
     @param x the x-coordinate of the upper-left point of the rectangle.
     @param y the y-coordinate of the upper-left point of the rectangle.
     @param width the width of the rectangle.
     @param height the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        Point start, end;
        start = this.getUpperLeft();
        end = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        this.rib1 = new Line(start, end);
        start = this.getUpperLeft();
        end = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        this.rib2 = new Line(start, end);
        start = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
        end = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        this.rib3 = new Line(start, end);
        start = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        end = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
        this.rib4 = new Line(start, end);
    }
    /**
     Returns a (possibly empty) list of intersection points of the rectangle with the specified line.
     @param line the line to check for intersection with the rectangle.
     @return a list of intersection points between the rectangle and the line, or an empty list if there are no
     intersections.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // indicator for avoid duplicates.
        boolean samePoint = false;
        java.util.List<Point> pointList = new ArrayList<>();
        //represent the rib of the rectangle.
        Line rib;
        Point start, end, intersectionPoint;
        //first rib
        start = this.upperLeft;
        end = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        rib = new Line(start, end);
        intersectionPoint = line.intersectionWith(rib);
        if (line.isIntersecting(rib) && intersectionPoint == null) {
            return new ArrayList<>();
        }
        if (intersectionPoint != null) {
            pointList.add(intersectionPoint);
        }
        //second rib
        start = this.upperLeft;
        end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        rib = new Line(start, end);
        intersectionPoint = line.intersectionWith(rib);
        if (line.isIntersecting(rib) && intersectionPoint == null) {
            return new ArrayList<>();
        }
        if (!pointList.isEmpty() && intersectionPoint != null) {
            for (Point p : pointList) {
                if (p.equals(intersectionPoint)) {
                    samePoint = true;
                }
            }
        }
        if (intersectionPoint != null && !samePoint) {
            pointList = new ArrayList<>();
                   pointList .add(intersectionPoint);

        }
        // third rib
        samePoint = false;
        start = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        rib = new Line(start, end);
        intersectionPoint = line.intersectionWith(rib);
        if (line.isIntersecting(rib) && intersectionPoint == null) {
            return new ArrayList<>();
        }
        if (!pointList.isEmpty() && intersectionPoint != null) {
            for (Point p : pointList) {
                if (p.equals(intersectionPoint)) {
                    samePoint = true;
                }
            }
        }
        if (intersectionPoint != null && !samePoint) {
            pointList.add(intersectionPoint);
        }
        // fourth rib
        samePoint = false;
        start = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + height);
        rib = new Line(start, end);
        intersectionPoint = line.intersectionWith(rib);
        if (line.isIntersecting(rib) && intersectionPoint == null) {
            return new ArrayList<>();
        }
        if (!pointList.isEmpty() && intersectionPoint != null) {
            for (Point p : pointList) {
                if (p.equals(intersectionPoint)) {
                    samePoint = true;
                }
            }
        }
        if (intersectionPoint != null && !samePoint) {
            pointList.add(intersectionPoint);
        }
        return pointList;
    }
    /**
     Returns the width of the rectangle.
     @return the width of the rectangle as a double
     */
    public double getWidth() {
        return this.width;
    }
    /**
     Returns the height of the rectangle.
     @return the height of the rectangle as a double
     */
    public double getHeight() {
        return this.height;
    }
    /**
     Returns the upper-left point of the rectangle.
     @return the upper-left point of the rectangle as a Basis.Point object
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     Returns the first side of the rectangle.
     @return the first side of the rectangle
     */
    public Line getRib1() {
        return rib1;
    }
    /**
     Returns the second side of the rectangle.
     @return the second side of the rectangle
     */
    public Line getRib2() {
        return rib2;
    }
    /**

     Returns the third side of the rectangle.
     @return the third side of the rectangle
     */
    public Line getRib3() {
        return rib3;
    }
    /**
     Returns the fourth side of the rectangle.
     @return the fourth side of the rectangle
     */
    public Line getRib4() {
        return rib4;
    }
}