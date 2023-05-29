package Collidable;

import Basis.Point;

/**
 * A class representing collision information between two objects.
 */
public class CollisionInfo {
    private Collidable collidable;
    private Point point;
    /**
     Constructs a new Collidable.Collidable.CollisionInfo object.
     @param collidable the collidable object involved in the collision
     @param point the point at which the collision occurs
     */
    public CollisionInfo(Collidable collidable, Point point) {
        this.collidable = collidable;
        this.point = point;
    }
    /**
     Returns the point at which the collision occurs.
     @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return point;
    }
    /**
     Returns the collidable object involved in the collision.
     @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return collidable;
    }
}