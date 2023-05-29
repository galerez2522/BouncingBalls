package Game;

import Basis.Point;
import Basis.Rectangle;
import Collidable.Collidable;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Sprite.Ball;
import Sprite.Sprite;
import Sprite.SpriteCollection;
import Collidable.Block;
import Collidable.Paddle;

import java.awt.Color;
/**
 This class represents a simple game consisting of blocks, a ball, and a paddle.
 It uses the GUI and sprite interfaces to display and animate the game objects.
 */
public class Game {
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    /**
     Adds a new collidable object to the game environment.
     @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     Adds a new sprite object to the game.
     @param s The sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     Initializes a new game, creating the blocks, ball, and paddle and adding them to the game.
     */
    public void initialize() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection(this.environment);
        this.gui = new GUI("Collidable.Game.Game", 800, 600);
        Block background = new Block(new Rectangle(0, 0, 800, 600), Color.pink);
        //the gray blocks
        Block block1 = new Block(new Rectangle(0, 0, 10, 600), Color.gray);
        Block block2 = new Block(new Rectangle(10, 0, 790, 10), Color.gray);
        Block block3 = new Block(new Rectangle(790, 10, 10, 590), Color.gray);
        Block block4 = new Block(new Rectangle(10, 590, 780, 10), Color.gray);

        int i;
        Paddle p = new Paddle(new Point(400, 570), 100, 20, gui);
        background.addToGame(this);
        p.addToGame(this);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);
        for (i = 1; i <= 11; i++) {
            Block block = new Block(new Rectangle(790 - (50 * i), 150, 50, 20), Color.orange);
            block.addToGame(this);
        }
        for (i = 1; i <= 10; i++) {
            Block block = new Block(new Rectangle(790 - (50 * i), 170, 50, 20), Color.red);
            block.addToGame(this);
        }
        for (i = 1; i <= 9; i++) {
            Block block = new Block(new Rectangle(790 - (50 * i), 190, 50, 20), Color.yellow);
            block.addToGame(this);
        }
        for (i = 1; i <= 8; i++) {
            Block block = new Block(new Rectangle(790 - (50 * i), 210, 50, 20), Color.blue);
            block.addToGame(this);
        }
        for (i = 1; i <= 7; i++) {
            Block block = new Block(new Rectangle(790 - (50 * i), 230, 50, 20), Color.darkGray);
            block.addToGame(this);
        }
        for (i = 1; i <= 6; i++) {
            Block block = new Block(new Rectangle(790 - (50 * i), 250, 50, 20), Color.green);
            block.addToGame(this);
        }
        Ball ball = new Ball(754, 100, 10, Color.black, this.environment);
        ball.setVelocity(-4, -4);
        ball.addToGame(this);
        Ball ball2 = new Ball(200, 200, 10, Color.magenta, this.environment);
        ball2.setVelocity(-4, -4);
        ball2.addToGame(this);
    }
    /**
     The run method is responsible for starting the game animation loop.
     It uses the GUI instance to get the DrawSurface and draw all the sprites on it,
     show the GUI with the updated DrawSurface, notify all sprites about time passed,
     and check if the ball hit the bottom of the screen.
     The method calculates the time used for drawing the frame and subtracts it from the time left to sleep.
     The loop runs indefinitely until the game is stopped manually.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
               this.sprites.ifTheBallStack1();
            //this.sprites.ifTheBallStack2();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     The main method creates a new Collidable.Game.Game instance, initializes it,
     and starts the game animation loop.
     @param args command-line arguments (not used in this implementation).
     */
}