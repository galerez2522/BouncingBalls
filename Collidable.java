package Collidable;

import Basis.Point;
import Basis.Rectangle;
import Basis.Velocity;

/**
 The Collidable.Collidable interface represents objects that can collide with other objects.
 */
public interface Collidable {
    /**
     Returns the "collision shape" of the object.
     @return a Basis.Rectangle object representing the collision shape of the object.
     */
    Rectangle getCollisionRectangle();
/**
 Notifies the object that a collision occurred at the given collisionPoint with
 the given currentVelocity.
 @param collisionPoint the Basis.Point at which the collision occurred.
 @param currentVelocity the Basis.Velocity of the object at the time of the collision.
 @return a Basis.Velocity object representing the new velocity expected after the hit
 **/
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

}