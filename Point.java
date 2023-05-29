package Basis;

/**
 * point class describe point with two parameters: x coordinate and y coordinate.
 */
public class Point {
    private double x;
    private double y;
    /**
     Constructs a new Basis.Point object with the specified x and y coordinates.
     @param x the x-coordinate of the point
     @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     Calculates the distance between this point and another point.
     @param other the other point to calculate the distance to
     @return the distance between this point and the other point
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
    /**
     Determines whether this point is equal to another point.
     Two points are considered equal if their x and y coordinates are within the specified threshold.
     @param other the other point to compare to
     @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (compareDouble(this.y, other.y, 0.00001) && compareDouble(this.x, other.x, 0.00001));
    }
    /**
     Returns the x-coordinate of this point.
     @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     Returns the y-coordinate of this point.
     @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
    /**
     Compares two double values with a specified threshold to account for floating-point precision errors.
     @param x the first double value to compare
     @param y the second double value to compare
     @param threshold the maximum difference between x and y for them to be considered equal
     @return true if x and y are within the specified threshold, false otherwise
     */
    public static boolean compareDouble(double x, double y, double threshold) {
        double diff = Math.abs(x - y);
        return diff <= threshold;
    }

}
