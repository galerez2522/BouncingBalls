package Sprite;

import biuoop.DrawSurface;
import Basis.Velocity;
import Basis.Point;
import Game.GameEnvironment;
import Game.Game;
import Collidable.CollisionInfo;
import Sprite.Sprite;
import Basis.Line;
/**
 The sprite.Ball class represents a ball object in a 2D space with a center point, a radius, a color and a velocity vector.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment game;

    // constructor
    /**
     * Constructor for creating a ball object with given center point, radius and color.
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param g the game environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = center;
        this.color = color;
        this.r = r;
        this.game = g;
        this.v = new Velocity(0, 0);
    }
    /**
     * Constructor for creating a ball object with given x and y coordinates, radius and color.
     * @param x the x coordinate of the center point of the ball
     * @param y the y coordinate of the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param game the game environment of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment game) {
        this.center = new Point(x, y);
        this.color = color;
        this.r = r;
        this.v = new Velocity(0, 0);
        this.game = game;
    }
    /**
     * Returns the x coordinate of the center point of the ball.
     * @return the x coordinate of the center point of the ball
     */
    // accessors
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * Returns the y coordinate of the center point of the ball.
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Returns the radius of the ball.
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }
    /**
     * Returns the color of the ball.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Draws the ball on the given DrawSurface.
     * @param surface the DrawSurface to draw the ball on
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    @Override
    public void timePassed() {
     moveOneStep();
    }

    /**
     * Sets the velocity vector of the ball to the given velocity.
     * @param v the new velocity vector of the ball
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }
    /**
     * Sets the velocity vector of the ball to the given x and y velocities.
     * @param dx the x component of the new velocity vector of the ball
     * @param dy the y component of the new velocity vector of the ball
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * Returns the velocity vector of the ball.
     * @return the velocity vector of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     This method moves the ball one step according to its velocity.
     It updates the center point of the ball by applying its velocity.
     The new center point is calculated by calling the applyToPoint() method of the ball's velocity.
     */
    public void moveOneStep() {
        double newCenterX;
        double newCenterY;
        Line trajectory;
        double x, y;
        if (this.getVelocity().getDx() < 0) {
            x = -this.r + this.getVelocity().getDx() + this.center.getX();
        } else {
            x = this.r + this.getVelocity().getDx() + this.center.getX();
        }
        if (this.getVelocity().getDy() > 0) {
            y = this.r + this.getVelocity().getDy() + this.center.getY();
        } else {
            y = -this.r + this.getVelocity().getDy() + this.center.getY();
        }
        trajectory = new Line(this.center, new Point(x, y));
        CollisionInfo c = game.getClosestCollision(trajectory);
        if (c == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            if (this.getVelocity().getDx() < 0) {
                newCenterX = c.collisionPoint().getX() + this.r;
            } else {
                newCenterX = c.collisionPoint().getX() - this.r;
            }
            if (this.getVelocity().getDy() < 0) {
                newCenterY = c.collisionPoint().getY() + this.r;
            } else {
                newCenterY = c.collisionPoint().getY() - this.r;
            }
            this.center = new Point(newCenterX, newCenterY);
            this.setVelocity(c.collisionObject().hit(c.collisionPoint(), this.getVelocity()));
        }
    }
    /**
     Adds this ball as a sprite to the game.
     @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     Checks if a given value is in range of two other values.
     @param x the value to check
     @param y the lower bound of the range
     @param z the upper bound of the range
     @return true if x is within the range [y, z], false otherwise
     */
    public Boolean inRange(double x, double y, double z) {
        return Math.max(y, z) >= x && Math.min(y, z) <= x;
    }
}

