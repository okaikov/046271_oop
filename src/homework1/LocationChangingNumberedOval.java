package homework1;

import java.awt.*;

/**
 * A LocationChangingNumberedOval is a LocationChangingOval with serial number.
 * The properties of LocationChangingNumberedOval are: {location, color, size, velocity, serial number}.
 * The serial number is incremented each time a new LocationChangingNumberedOval is created.
 */
public class LocationChangingNumberedOval extends LocationChangingOval {

    // Abstraction Function: Represents an Oval with serial number serialNum and all the other properties, represented
    //                       by the base class.

    // Representation Invariant: serialNum >= INITIAL_SERIAL_NUM. (INITIAL_SERIAL_NUM is a class constant)

    static final int INITIAL_SERIAL_NUM = 1;
    static int ovalsCounter = INITIAL_SERIAL_NUM;

    int serialNum;


    /**
     * @effects Initializes this with a a given location, color and dimensions {dim}.
     *          If dimension is null-reference or one of the components (X or Y) of dimension is negative, an
     *          AssertionError is thrown.
     * @modifies ovalsCounter
     */
    public LocationChangingNumberedOval(Point location, Color color, Dimension dim) {
        super(location, color, dim);
        serialNum = ovalsCounter;
        ovalsCounter++;

        checkRep();
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g) {
        checkRep();
        super.draw(g);

        Graphics2D g2d = (Graphics2D)g;

        // Calculate the left upper corner of the text of serial number.
        // Currently a primitive calculation is used. The sophisticated should take into account
        // the size of the font and the number of digits in the serial number.
        Point leftUpperCornerOfText = new Point(
                (int)(getBounds().getMaxX() + getBounds().getMinX()) / 2,
                (int)(getBounds().getMaxY() + getBounds().getMinY()) / 2
        );

        if(getColor() != Color.BLACK) {
            g2d.setColor(Color.BLACK);
        } else {
            g2d.setColor(Color.WHITE);
        }

        g2d.drawString(
                Integer.toString(serialNum),
                (int)leftUpperCornerOfText.getX(),
                (int)leftUpperCornerOfText.getY());
        checkRep();
    }

    /**
     * @effects Creates and returns a copy of this.
     *          The oval number is copied from this.
     */
    @Override
    public Object clone(){
        checkRep();

        LocationChangingNumberedOval clonedOval = null;

        clonedOval = (LocationChangingNumberedOval) super.clone();

        clonedOval.serialNum = this.serialNum;

        checkRep();
        return clonedOval;
    }

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is violated.
     */
    private void checkRep() {
        assert serialNum >= INITIAL_SERIAL_NUM : "Serial number of Numbered Oval is illegal";
    }
}
