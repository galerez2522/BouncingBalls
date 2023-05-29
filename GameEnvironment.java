package Game;

import Basis.Line;
import Basis.Point;
import Collidable.Collidable;
import Collidable.CollisionInfo;
import java.util.ArrayList;
/**
 A class representing the game environment, containing a list of collidables.
 */
public class GameEnvironment {
    private java.util.List<Collidable> listOfCollidable;

    /**
     Constructor for the Game.GameEnvironment class.
     Initializes an empty list of collidables.
     */
    public GameEnvironment() {
        this.listOfCollidable = new ArrayList<Collidable>();
    }
    /**
     Adds a given collidable to the environment.
     @param c the collidable to be added to the environment.
     */
    public void addCollidable(Collidable c) {
        this.listOfCollidable.add(c);
    }
    /**
     Returns the list of collidables in the environment.
     @return the list of collidables in the environment.
     */
    public java.util.List<Collidable> getListOfCollidable() {
        return this.listOfCollidable;
    }
    /**
     Assumes an object moving from line.start() to line.end().
     If this object will not collide with any of the collidables
     in this collection, return null. Else, return the information
     about the closest collision that is going to occur.
     @param trajectory the line representing the object's trajectory.
     @return the information about the closest collision that is going to occur,
     or null if no collision will occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closetCollisionPoint = trajectory.end();
        Collidable closetCollidable = null;
        //checking all the collides.
       for (Collidable collidable : listOfCollidable) {
           //take collision point (if there is)
           Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
           if (collisionPoint != null) {
               collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
           }
           //minimum distance check.
           if (collisionPoint != null && collisionPoint.distance(trajectory.start())
                   <= closetCollisionPoint.distance(trajectory.start())) {
               closetCollidable = collidable;
               closetCollisionPoint = collisionPoint;
           }
       }
       // if we didn't found any collision
       if (closetCollidable == null) {
           return null;
       }
       return new CollisionInfo(closetCollidable, closetCollisionPoint);
    }
}