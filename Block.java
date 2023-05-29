package Collidable;

import Basis.Point;
import Basis.Rectangle;
import Basis.Velocity;
import Game.Game;
import biuoop.DrawSurface;
import Sprite.Sprite;

import java.awt.Color;
/**
 A Collidable.Collidable.Block class that implements the Collidable.Collidable and sprite.Sprite interfaces.
 */
public class Block implements Collidable, Sprite {
    private boolean ifHit;
    private Rectangle rect;
    private Color color;
    /**
     Construct a Collidable.Collidable.Block given a rectangle and a color.
     @param rect the rectangle of the Collidable.Collidable.Block.
     @param color the color of the Collidable.Collidable.Block.
     */
    public Block(Rectangle rect, Color color) {
        this.ifHit = false;
        this.rect = rect;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
     int whatRib1 = whatRib1(collisionPoint);
     int whatRib2 = whatRib2(collisionPoint);
     double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
     if (whatRib1 != 5 && whatRib2 != 5) {
         dx = -currentVelocity.getDx();
         dy = -currentVelocity.getDy();
     }
     if (whatRib1 == 1 || whatRib1 == 3) {
         dx = -currentVelocity.getDx();
     } else if (whatRib2 == 2 || whatRib2 == 4) {
         dy = -currentVelocity.getDy();
     }
     this.ifHit = true;
     return new Velocity(dx, dy);
    }
    /**
     Check which of the Collidable.Collidable.Block's ribs was hit (the parallel to y line).
     @param collisionPoint the point of collision.
     @return the number of the hit rib, or 5 if no rib was hit.
     */
    public int whatRib1(Point collisionPoint) {
        //check first rib
        if ((inRange(collisionPoint.getY(), rect.getRib1().getStart().getY(),
                rect.getRib1().getEnd().getY())
                || inRange(collisionPoint.getY(), rect.getRib1().getEnd().getY(), rect.getRib1().getStart().getY()))
                && collisionPoint.getX() == rect.getRib1().getStart().getX()) {
            return 1;
        }
        //check third rib
        if ((inRange(collisionPoint.getY(), rect.getRib3().getStart().getY(), rect.getRib3().getEnd().getY())
                || inRange(collisionPoint.getY(), rect.getRib3().getEnd().getY(), rect.getRib3().getStart().getY()))
                && collisionPoint.getX() == rect.getRib3().getStart().getX()) {
            return 3;
        }
        return 5;
    }
    /**

     Check which of the Collidable.Collidable.Block's ribs was hit (the parallel to x line).
     @param collisionPoint the point of collision.
     @return the number of the hit rib, or 5 if no rib was hit.
     */
    public int whatRib2(Point collisionPoint) {
        //check second rib
        if ((inRange(collisionPoint.getX(), rect.getRib2().getStart().getX(), rect.getRib2().getEnd().getX())
                || inRange(collisionPoint.getX(), rect.getRib2().getEnd().getX(), rect.getRib2().getStart().getX()))
                && collisionPoint.getY() == rect.getRib2().getStart().getY()) {
            return 2;
        }
        //check fourth rib
        if ((inRange(collisionPoint.getX(), rect.getRib4().getStart().getX(), rect.getRib4().getEnd().getX())
                || inRange(collisionPoint.getX(), rect.getRib4().getEnd().getX(), rect.getRib4().getStart().getX()))
                && collisionPoint.getY() == rect.getRib4().getStart().getY()) {
            return 4;
        }
        return 5;
    }
    /**
     Draws the block on the given surface.
     The block is filled with the block's color and outlined with black.
     @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());


    }
    @Override
    public void timePassed() {
    }
    /**
     Adds this block as both a collidable object and a sprite to the game.
     @param g the game to add the block to
     */
    public void addToGame(Game g) {
    g.addCollidable(this);
    g.addSprite(this);
}
    /**
     Checks if a given value is in range of two other values.
     @param x the value to check
     @param y the lower bound of the range
     @param z the upper bound of the range
     @return true if x is within the range [y, z], false otherwise
     */
    public boolean inRange(double x, double y, double z) {
        return x >= y && x <= z;
    }
    /**
     Returns the rectangle that defines this block.
     @return the rectangle that defines this block
     */
    public Rectangle getRect() {
        return this.rect;
    }
    /**
     Returns the color of this block.
     @return the color of this block
     */
    public Color getColor() {
        return this.color;
    }
}
