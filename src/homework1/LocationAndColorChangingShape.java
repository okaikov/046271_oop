package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A ColorAndLocationChaningShape is a Shape that can change its location and color using its step()
 * method.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationAndColorChangingShape extends LocationChangingShape{

    // Abstraction Function: Represents shape, which location can change as described in
    // LocationChangingShape.step(Rectangle bound) and color can change as described in this.step(Rectangle bound).
    // The concrete type is empty set.

    // Representation Invariant: All values {of empty set}


    /**
     * @effects Initializes this with a a given location and color. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    LocationAndColorChangingShape(Point location, Color color) {
        super(location, color);
    }

    /**
     * @modifies this
     * @effects Changes the location of this as described in the specification
     *          of LocationChangingShape.step(Rectangle bound) &&
	 *			if the velocity of this needs to be changed (as described in LocationChangingShape.step(Rectangle bound)),
	 *			changes the color of this to a new random color;
	 *			else, does not change the color of this.
     *@requires bound != null (otherwise, throws AssertionError)
     */
    public void step(Rectangle bound) {
        checkRep();
        assert bound != null : "bound is a null reference in step().";

        int velocityX = super.getVelocityX();
        int velocityY = super.getVelocityY();
        super.step(bound);
        if (velocityX != super.getVelocityX() || velocityY != super.getVelocityY()){
            Random rnd = new Random();
            super.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        }
        checkRep();
    }

    private void checkRep(){
        // Intentionally empty
    }
}
