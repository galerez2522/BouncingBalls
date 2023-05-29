package Basis;

/**
 Basis.Velocity specifies the change in position on the x and the y axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    // constructor
    /**
     Constructor for Basis.Velocity class.
     @param dx the change in position on the x-axis.
     @param dy the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     Returns the change in position on the x-axis.
     @return the value of dx.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     Returns the change in position on the y-axis.
     @return the value of dx.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     Changes the sign of dx.
     */
    public void changeSignDx() {
        this.dx = -this.dx;
    }
    /**
     Changes the sign of dy.
     */
    public void changeSignDy() {
        this.dy = -this.dy;
    }
    /**
     Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     @param p the point to apply the velocity on.
     @return the new point with the applied velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }
    /**
     Creates a velocity with a given angle and speed.
     @param angle the angle of the velocity vector in degrees.
     @param speed the speed of the velocity.
     @return a new Basis.Velocity object with the calculated dx and dy values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians); // negative because up is angle 0
        return new Velocity(dx, dy);
    }
    }