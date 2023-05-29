package Sprite;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import Game.GameEnvironment;
import Game.Game;
import Basis.Rectangle;
import Basis.Line;
import Basis.Point;
import Collidable.Block;
import Collidable.Paddle;
/**
 The sprite.SpriteCollection class represents a collection of sprites in the game.
 It contains methods to add a sprite, notify all sprites of the passage of time,
 and draw all sprites on a DrawSurface. It also contains a method to check if the ball
 collides with any of the blocks or the paddle and updates the ball's position and velocity accordingly.
 */
public class SpriteCollection {
    private final java.util.List<Sprite> spriteList;
    private final GameEnvironment gameEnvironment;
    /**
     Constructor for creating a new sprite.SpriteCollection object.
     @param gameEnvironment the game environment of the sprite collection.
     */
    public SpriteCollection(GameEnvironment gameEnvironment) {
        this.spriteList = new ArrayList<Sprite>();
        this.gameEnvironment = gameEnvironment;
    }
    /**
     Adds a sprite to the collection.
     @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
    this.spriteList.add(s);
    }
    /**
     Calls the timePassed() method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : this.spriteList) {
            sprite.timePassed();
        }
    }
    /**
     Calls the drawOn(d) method on all sprites in the collection.
     @param d the surface on which the sprites are drawn.
     */
    public void drawAllOn(DrawSurface d) {
    for (Sprite s : this.spriteList) {
        s.drawOn(d);
    }
    }
    /**
     Checks if the ball collides with any of the objects in the sprite collection.
     */
    public void ifTheBallStack1() {
        Paddle paddle = (Paddle) this.spriteList.get(1);
        Ball ball = (Ball) this.spriteList.get(57);
        if (inRange(ball.getX(), paddle.getCollisionRectangle().getUpperLeft().getX(),
                paddle.getCollisionRectangle().getUpperLeft().getX() + paddle.getCollisionRectangle().getWidth())
                && inRange(ball.getY(), paddle.getCollisionRectangle().getUpperLeft().getY(),
                paddle.getCollisionRectangle().getUpperLeft().getY() + paddle.getCollisionRectangle().getHeight())) {
            ball = new Ball(ball.getX(), ball.getY() - 32, ball.getSize(), Color.black, this.gameEnvironment);
            ball.setVelocity(-((Ball) this.spriteList.get(57)).getVelocity().getDx(),
                    ((Ball) this.spriteList.get(57)).getVelocity().getDy());
        }
            this.spriteList.set(57, ball);
            for (int i = 2; i < 57; i++) {
                Block block = (Block) this.spriteList.get(i);
                Rectangle rect = block.getCollisionRectangle();
                if (checkOnRect(new Point(ball.getX(), ball.getY()), rect)) {
                    if (block.getColor() == Color.gray) {
                        if (rect.getUpperLeft().equals(new Point(0, 0))) {
                            ball = new Ball(ball.getX() + 10, ball.getY(), ball.getSize(),
                                    Color.black, this.gameEnvironment);
                        }
                        if (rect.getUpperLeft().equals(new Point(10, 0))) {
                            ball = new Ball(ball.getX(), ball.getY() + 10, ball.getSize(),
                                    Color.black, this.gameEnvironment);
                        }
                        if (rect.getUpperLeft().equals(new Point(790, 10))) {
                            ball = new Ball(ball.getX(), ball.getY() - 10, ball.getSize(),
                                    Color.black, this.gameEnvironment);
                        }
                        if (rect.getUpperLeft().equals(new Point(10, 590))) {
                            ball = new Ball(ball.getX(), ball.getY() - 10, ball.getSize(),
                                    Color.black, this.gameEnvironment);
                        }
                    }
                    if (block.getColor() == Color.green) {
                        ball = new Ball(ball.getX(), ball.getY() + 20, ball.getSize(),
                                Color.black, this.gameEnvironment);
                    }
                    if (block.getColor() == Color.orange) {
                        ball = new Ball(ball.getX(), ball.getY() - 20, ball.getSize(),
                                Color.black, this.gameEnvironment);
                    }
                    ball.setVelocity(((Ball) this.spriteList.get(57)).getVelocity().getDx(),
                            ((Ball) this.spriteList.get(57)).getVelocity().getDy());
                    this.spriteList.set(57, ball);
                    return;
                }
            }

    }
    public void ifTheBallStack2() {
        Paddle paddle = (Paddle) this.spriteList.get(1);
        Ball ball = (Ball) this.spriteList.get(58);
        if (inRange(ball.getX(), paddle.getCollisionRectangle().getUpperLeft().getX(),
                paddle.getCollisionRectangle().getUpperLeft().getX() + paddle.getCollisionRectangle().getWidth())
                && inRange(ball.getY(), paddle.getCollisionRectangle().getUpperLeft().getY(),
                paddle.getCollisionRectangle().getUpperLeft().getY() + paddle.getCollisionRectangle().getHeight())) {
            ball = new Ball(ball.getX(), ball.getY() - 32, ball.getSize(), Color.black, this.gameEnvironment);
            ball.setVelocity(-((Ball) this.spriteList.get(58)).getVelocity().getDx(),
                    ((Ball) this.spriteList.get(58)).getVelocity().getDy());
        }
        this.spriteList.set(58, ball);
        for (int i = 2; i < 57; i++) {
            Block block = (Block) this.spriteList.get(i);
            Rectangle rect = block.getCollisionRectangle();
            if (checkOnRect(new Point(ball.getX(), ball.getY()), rect)) {
                if (block.getColor() == Color.gray) {
                    if (rect.getUpperLeft().equals(new Point(0, 0))) {
                        ball = new Ball(ball.getX() + 10, ball.getY(), ball.getSize(),
                                Color.black, this.gameEnvironment);
                    }
                    if (rect.getUpperLeft().equals(new Point(10, 0))) {
                        ball = new Ball(ball.getX(), ball.getY() + 10, ball.getSize(),
                                Color.black, this.gameEnvironment);
                    }
                    if (rect.getUpperLeft().equals(new Point(790, 10))) {
                        ball = new Ball(ball.getX(), ball.getY() - 10, ball.getSize(),
                                Color.black, this.gameEnvironment);
                    }
                    if (rect.getUpperLeft().equals(new Point(10, 590))) {
                        ball = new Ball(ball.getX(), ball.getY() - 10, ball.getSize(),
                                Color.black, this.gameEnvironment);
                    }
                }
                if (block.getColor() == Color.green) {
                    ball = new Ball(ball.getX(), ball.getY() + 20, ball.getSize(),
                            Color.black, this.gameEnvironment);
                }
                if (block.getColor() == Color.orange) {
                    ball = new Ball(ball.getX(), ball.getY() - 20, ball.getSize(),
                            Color.black, this.gameEnvironment);
                }
                ball.setVelocity(((Ball) this.spriteList.get(58)).getVelocity().getDx(),
                        ((Ball) this.spriteList.get(58)).getVelocity().getDy());
                this.spriteList.set(58, ball);
                return;
            }
        }

    }
    /**
     * Returns true if x is within the range defined by y and z, inclusively.
     * @param x The value to check if it is within range.
     * @param y The lower bound of the range to check.
     * @param z The upper bound of the range to check.
     * @return True if x is within the range defined by y and z, inclusively, false otherwise.
     */
    public Boolean inRange(double x, double y, double z) {
        return Math.max(y, z) >= x && Math.min(y, z) <= x;
    }
    /**
     * Returns true if the given point is within the bounds of the given rectangle.
     * @param center The point to check.
     * @param rect The rectangle to check against.
     * @return True if the given point is within the bounds of the given rectangle, false otherwise.
     */
    public Boolean checkOnRect(Point center, Rectangle rect) {
        return inRange(center.getX(), rect.getUpperLeft().getX(), rect.getUpperLeft().getX() + rect.getWidth())
                && inRange(center.getY(), rect.getUpperLeft().getY(), rect.getUpperLeft().getY() + rect.getHeight());
    }
}