package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {

    // Abstraction Function: Represents a shape that moves on every step by (velocityX, velocityY) graphical distance
    // units.

    // Representation Invariant: velocityX, velocityY are integral numbers,
    // velocityX, velocityY are elements of {([-MAX_ABSOLUTE_VELOCITY, MAX_ABSOLUTE_VELOCITY]) ∩ Z}.

    private int velocityX;
    private int velocityY;

    private static final int MAX_ABSOLUTE_VELOCITY = 5;


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
        checkRep();
    }

    /**
     *
     * @effects if bound != 0, returns an integer random non-zero number between -abs(bound) and +abs(bound) inclusive,
     *          otherwise, returns 0.
     * @modifies nothing
     * @requires nothing
     */
    private static int randNoZeroNum(int bound)
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
        checkRep();
        return velocityX;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        checkRep();
        return velocityY;
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     *          vertical velocity of this to velocityY.
     * @requires -5 <= velocityX <= 5 && -5 <= velocityY <= 5
     */
    public void setVelocity(int velocityX, int velocityY) {
        checkRep();
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        checkRep();
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
     * @requires bound != null. (Otherwise throws AssertionError).
     */
    public void step(Rectangle bound) {
        checkRep();
        // Copy shape-bounding rectangle to local object, because getBounds() is abstract, and nobody promises that
        // it will not return the reference to the internal object representing the bounds itself.
        assert bound != null : "bound is null reference";
        Rectangle shapeBounding = new Rectangle(getBounds());
        Rectangle movedShapeBounding = new Rectangle(
                (int)shapeBounding.getX() + velocityX,
                (int)shapeBounding.getY() + velocityY,
                (int)shapeBounding.getWidth(),
                (int)shapeBounding.getHeight());

        // Check if exceeds the bounds.

        boolean isInHorizontalBound = isRectInHorizontalBound(bound, shapeBounding);
        boolean isInVerticalBound = isRectInVerticalBound(bound, shapeBounding);
        boolean isMovedInHorizontalBound = isRectInHorizontalBound(bound, movedShapeBounding);
        boolean isMovedInVerticalBound = isRectInVerticalBound(bound, movedShapeBounding);

        // Update the velocity if necessary and update the location.
        Point newLocation = new Point((int)shapeBounding.getX(), (int)shapeBounding.getY());

        if(!isInHorizontalBound || !isMovedInHorizontalBound) {
            velocityX = -velocityX;
        }
        if(!isInVerticalBound || !isMovedInVerticalBound) {
            velocityY = -velocityY;
        }

        // Perform the step on newLocation
        newLocation.translate(velocityX, velocityY);

        // Modify the internal variables.
        setLocation(newLocation);

        checkRep();

    }

    /**
     *
     * @effects If vertical bounds of shapeBounding rectangle are included into vertical bounds of bound rectangle,
     *          returns true, otherwise returns false.
     * @modifies nothing
     * @requires bound != null, shapeBounding != null. Otherwise throws AssertionError.
     */
    private static boolean isRectInHorizontalBound(Rectangle bound, Rectangle shapeBounding)
    {
        assert (bound != null && shapeBounding != null) : "null reference";
        return (bound.getMinX() <= shapeBounding.getMinX()) && (shapeBounding.getMaxX() <= bound.getMaxX());
    }

    /**
     *
     * @effects If horizontal bounds of shapeBounding rectangle are included into horizontal bounds of bound rectangle,
     *          returns true, otherwise returns false.
     * @modifies nothing
     * @requires bound != null, shapeBounding != null. Otherwise throws AssertionError.
     */
    private static boolean isRectInVerticalBound(Rectangle bound, Rectangle shapeBounding)
    {
        assert (bound != null && shapeBounding != null) : "null reference";
        return (bound.getMinY() <= shapeBounding.getMinY()) && (shapeBounding.getMaxY() <= bound.getMaxY());
    }


    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
        checkRep();

        LocationChangingShape clonedShape = null;

        clonedShape = (LocationChangingShape) super.clone();

        clonedShape.velocityX = this.velocityX;
        clonedShape.velocityY = this.velocityY;

        checkRep();
        return clonedShape;
    }

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is violated.
     * @effects Throws AssertionError if representation invariant is violated.
     * @modifies nothing
     * @requires nothing
     */
    private void checkRep() {
        assert (velocityX <= MAX_ABSOLUTE_VELOCITY && velocityX >= -MAX_ABSOLUTE_VELOCITY && velocityX != 0):
                "velocityX value is not valid: " + velocityX;
        assert (velocityY <= MAX_ABSOLUTE_VELOCITY && velocityY >= -MAX_ABSOLUTE_VELOCITY && velocityY != 0):
                "velocityY value is not valid: " + velocityY;
    }

}
