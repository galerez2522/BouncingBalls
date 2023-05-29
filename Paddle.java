package Collidable;

import Basis.Point;
import Basis.Rectangle;
import Basis.Velocity;
import Game.Game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Sprite.Sprite;

import java.awt.Color;
/**
 A class that represents the Collidable.Paddle object in the game.
 Implements the sprite.Sprite and Collidable.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRect;
    private Color color;
    private GUI gui;
    private double width;
    private double height;
    /**
     Constructor.
     @param upperLeft the upper left corner point of the paddle
     @param width the width of the paddle
     @param height the height of the paddle
     @param gui the GUI object of the game
     */
    public Paddle(Point upperLeft, double width, double height, GUI gui) {
        this.paddleRect = new Rectangle(upperLeft, width, height);
        this.color = Color.yellow;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.width = width;
        this.height = height;
    }
    /**
     Moves the paddle left.
     */
    public void moveLeft() {
        this.paddleRect = new Rectangle(new Point(this.paddleRect.getUpperLeft().getX() - 4,
                this.paddleRect.getUpperLeft().getY()), this.paddleRect.getWidth(), this.paddleRect.getHeight());
    }
    /**
     Moves the paddle right.
     */
    public void moveRight() {
        this.paddleRect = new Rectangle(new Point(this.paddleRect.getUpperLeft().getX() + 4,
                this.paddleRect.getUpperLeft().getY()), this.paddleRect.getWidth(), this.paddleRect.getHeight());
    }
    /**
     This method is called when time passes in the game.
     Moves the paddle left or right according to the user's keyboard input.
     */
    public void timePassed() {
        Point thePreviousLocation = this.paddleRect.getUpperLeft();
     if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
         moveLeft();
         if (!checkLocation()) {
             this.paddleRect = new Rectangle(thePreviousLocation, width, height);
         }
     }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            if (!checkLocation()) {
                this.paddleRect = new Rectangle(thePreviousLocation, width, height);
            }
        }
    }
    /**
     Draws the paddle on the given DrawSurface.
     @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddleRect.getUpperLeft().getX(),
                (int) this.paddleRect.getUpperLeft().getY(),
                (int) this.paddleRect.getWidth(),
                (int) this.paddleRect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddleRect.getUpperLeft().getX(),
                (int) this.paddleRect.getUpperLeft().getY(),
                (int) this.paddleRect.getWidth(),
                (int) this.paddleRect.getHeight());
    }
    /**
     Returns the collision rectangle of the paddle.
     @return the collision rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return paddleRect;
    }
    /**
     Determines which of the paddle's sides was hit by the ball, and returns an integer value accordingly.
     @param collisionPoint the point of collision between the paddle and the ball
     @return an integer value representing the side of the paddle that was hit by the ball
     */
    public int whatRib(Point collisionPoint) {
        //check first rib
        if ((inRange(collisionPoint.getY(), paddleRect.getRib1().getStart().getY(),
                paddleRect.getRib1().getEnd().getY())
                || inRange(collisionPoint.getY(), paddleRect.getRib1().getEnd().getY(),
                        paddleRect.getRib1().getStart().getY()))
                && collisionPoint.getX() == paddleRect.getRib1().getStart().getX()) {
            return 1;
        }
        //check third rib
        if ((inRange(collisionPoint.getY(), paddleRect.getRib3().getStart().getY(),
                paddleRect.getRib3().getEnd().getY())
                || inRange(collisionPoint.getY(), paddleRect.getRib3().getEnd().getY(),
                paddleRect.getRib3().getStart().getY()))
                && collisionPoint.getX() == paddleRect.getRib3().getStart().getX()) {
            return 3;
        }
        //check second rib
        if ((inRange(collisionPoint.getX(), paddleRect.getRib2().getStart().getX(),
                paddleRect.getRib2().getEnd().getX())
                || inRange(collisionPoint.getX(), paddleRect.getRib2().getEnd().getX(),
                paddleRect.getRib2().getStart().getX()))
                && collisionPoint.getY() == paddleRect.getRib2().getStart().getY()) {
            return 2;
        }
        //check fourth rib
        if ((inRange(collisionPoint.getX(), paddleRect.getRib4().getStart().getX(),
                paddleRect.getRib4().getEnd().getX())
                || inRange(collisionPoint.getX(), paddleRect.getRib4().getEnd().getX(),
                paddleRect.getRib4().getStart().getX()))
                && collisionPoint.getY() == paddleRect.getRib4().getStart().getY()) {
            return 4;
        }
        return 5;
    }
    /**
     Calculates the new velocity of the ball after a collision with the paddle.
     @param collisionPoint the point of collision between the ball and the paddle
     @param currentVelocity the current velocity of the ball
     @return the new velocity of the ball after the collision with the paddle
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int whatRib = whatRib(collisionPoint);
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        if (whatRib == 1 || whatRib == 3) {
            dx = -currentVelocity.getDx();
        } else if (whatRib == 2 || whatRib == 4) {
            return velocityBetweenArea(collisionPoint, currentVelocity);
        }
        return new Velocity(dx, dy);
    }

    /**
     Adds the paddle to the game by adding it as both a sprite and a collidable object.
     @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
    g.addSprite(this);
    g.addCollidable(this);
    }
    /**
     Checks if the paddle's location is within a specified range.
     @return true if the paddle's location is within the specified range, false otherwise
     */
    public boolean checkLocation() {
        return inRange(this.paddleRect.getUpperLeft().getX(), 10, 690);
    }
    /**
     Calculates the new velocity of the ball after a collision with the paddle, taking into account
     the specific area of the paddle that the ball hit.
     @param collisionPoint the point of collision between the ball and the paddle
     @param currentVelocity the current velocity of the ball
     @return the new velocity of the ball after the collision with the paddle
     */
    public Velocity velocityBetweenArea(Point collisionPoint, Velocity currentVelocity) {
        double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                + (currentVelocity.getDy() * currentVelocity.getDy()));
        if (inRange(collisionPoint.getX(), this.paddleRect.getUpperLeft().getX(),
                this.paddleRect.getUpperLeft().getX() + 20)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (inRange(collisionPoint.getX(), this.paddleRect.getUpperLeft().getX() + 20,
                this.paddleRect.getUpperLeft().getX() + 40)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (inRange(collisionPoint.getX(), this.paddleRect.getUpperLeft().getX() + 40,
                this.paddleRect.getUpperLeft().getX() + 60)) {
            return Velocity.fromAngleAndSpeed(0, speed);
        }
        if (inRange(collisionPoint.getX(), this.paddleRect.getUpperLeft().getX() + 60,
                this.paddleRect.getUpperLeft().getX() + 80)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (inRange(collisionPoint.getX(), this.paddleRect.getUpperLeft().getX() + 80,
                this.paddleRect.getUpperLeft().getX() + 100)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        return null;
    }
    /**
     Checks if a given value is within a specified range.
     @param x the value to check
     @param y the lower bound of the range
     @param z the upper bound of the range
     @return true if the value is within the range, false otherwise
     */
    public Boolean inRange(double x, double y, double z) {
        return Math.max(y, z) >= x && Math.min(y, z) <= x;
    }
}