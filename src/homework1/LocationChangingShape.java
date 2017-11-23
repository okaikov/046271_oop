package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {

    // TODO (BOM): Write Abstraction Function

    // TODO (BOM): Write Representation Invariant

    private int velocityX;
    private int velocityY;

    final private int MAX_ABSOLUTE_VELOCITY = 5;


    /**
     * @effects Initializes this with a a given location and color. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    LocationChangingShape(Point location, Color color) {
        super(location, color);

        velocityX = randNoZeroNum(MAX_ABSOLUTE_VELOCITY);
        velocityY = randNoZeroNum(MAX_ABSOLUTE_VELOCITY);
    }

    private int randNoZeroNum(int bound)
    {
        if (bound == 0)
        {
            return 0;
        }
        bound = java.lang.Math.abs(bound);

        Random rnd = new Random();
        int randNumber = rnd.nextInt(bound) + 1;
        int randSign = rnd.nextInt(2);

        if (randSign == 0) // The number is negative
        {
            randNumber = -randNumber;
        }

        return randNumber;
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
        return velocityX;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        return velocityY;
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     *          vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }


    /**
     * @modifies this
     * @effects Let p = location
     *              v = (vx, vy) = velocity
     *              r = the bounding rectangle of this
     *          If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     *              If adding v to p would move r horizontally farther away
     *              from the center of bound,
     *                  vx = -vx
     *              If adding v to p would move r vertically farther away
     *              from the center of bound,
     *                  vy = -vy
     *          }
     *          p = p + v
     */
    public void step(Rectangle bound) {
        // TODO (BOM): Implement this method
        // Copy shape-bounding rectangle to local object, because getBounds() is abstract, and nobody promises that
        // it will not return the reference to the object itself.

        Rectangle shapeBounding = new Rectangle(getBounds());
        Rectangle movedShapeBounding = new Rectangle(
                (int)shapeBounding.getX() + velocityX,
                (int)shapeBounding.getY() + velocityY,
                (int)shapeBounding.getWidth(),
                (int)shapeBounding.getHeight());

        boolean isInHorizontalBound = isRectInHorizontalBound(bound, shapeBounding);
        boolean isInVerticalBound = isRectInVerticalBound(bound, shapeBounding);
        boolean isMovedInHorizontalBound = isRectInHorizontalBound(bound, movedShapeBounding);
        boolean isMovedInVerticalBound = isRectInVerticalBound(bound, movedShapeBounding);




    }

    private boolean isRectInHorizontalBound(Rectangle bound, Rectangle shapeBounding)
    {
        return (bound.getMinX() <= shapeBounding.getMinX()) && (shapeBounding.getMaxX() <= bound.getMaxX());
    }

    private boolean isRectInVerticalBound(Rectangle bound, Rectangle shapeBounding)
    {
        return (bound.getMinY() <= shapeBounding.getMinY()) && (shapeBounding.getMaxY() <= bound.getMaxY());
    }
/*
    private void isPointInBound(Point point, Rectangle bound, Boolean isInHorisontalBound, Boolean isInVerticalBound)
    {
        isInHorisontalBound = (point.getX() <= bound.getMaxX()) && (point.getX() >= bound.getMinX());
        isInVerticalBound = (point.getY() <= bound.getMaxY()) && (point.getY() >= bound.getMinY());
    }

    private void isRectangleInBound(Rectangle rectangle, Rectangle bound, Boolean isInHorisontalBound, Boolean isInVerticalBound)
    {
        Boolean vertexUpLeftIsInHorizontalBound = Boolean.FALSE;
        Boolean vertexUpLeftIsInVerticalBound = Boolean.FALSE;
        Boolean vertexDownRightIsInHorizontalBound = Boolean.FALSE;
        Boolean vertexDownRightIsInVerticalBound = Boolean.FALSE;

        Point upperLeft = rectangle.getLocation();
        Point downRight = new Point((int)(rectangle.getMaxX()), (int)(rectangle.getMaxY()));

        isPointInBound(upperLeft, bound, vertexUpLeftIsInHorizontalBound, vertexUpLeftIsInVerticalBound);
        isPointInBound(downRight, bound, vertexDownRightIsInHorizontalBound, vertexDownRightIsInVerticalBound);

        isInHorisontalBound = vertexUpLeftIsInHorizontalBound && vertexDownRightIsInHorizontalBound;
        isInVerticalBound = vertexDownRightIsInHorizontalBound && vertexDownRightIsInVerticalBound;
    }*/
}
