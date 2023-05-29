package Sprite;

import biuoop.DrawSurface;
/**
 * sprite interface.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is called once per game frame and allows the sprite to update itself.
     */
    void timePassed();
}