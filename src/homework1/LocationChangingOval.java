package homework1;

import java.awt.*;

/**
 * A LocationChangingOval is a LocationChangingShape that is drawn as filled oval.
 * The properties of LocationChangingOval are: {location, color, size, velocity}.
 * Location is the top-left corner of bounding rectangle of the oval,
 * Size is width and height of the oval,
 * Color is the fill color and outline color of the oval
 * Velocity is the number of graphic distance units the oval will move on each step() in X and Y dimension.
 */
public class LocationChangingOval extends LocationChangingShape {

    // Abstraction Function: Represents an Oval of size ovalSize. (ovalSize is represented as Dimension)
    // Note: the "bounding rectangle" of the oval is represented by ovalSize and location (of the left upper corner),
    // but the representation of location is provided by the base-class.

    // Representation Invariant: ovalSize != null; height, width of ovalSize >= 0.

    private Dimension ovalSize;

    /**
     * @effects Initializes this with a a given location, color and dimensions {dim}.
     *          If dimension is null-reference or one of the components (X or Y) of dimension is negative, an
     *          AssertionError is thrown.
     */
    LocationChangingOval(Point location, Color color, Dimension dim) {
        super(location, color);
        // Check input parameters except for base-class parameters.
        assert dim != null : "LocationChangingOval was created with null-reference dimension";
        assert dim.getHeight() >= 0 && dim.getWidth() >= 0 : "LocationChangingOval was created with negative size";

        ovalSize = new Dimension((int)dim.getWidth(), (int)dim.getHeight());

        checkRep();
    }

    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     *          dimension.
     *          If this cannot be resized to the specified dimension
     *          (specifically, if dimension is a null-reference or its X or Y are negative) =>
     *          this is not modified, throws ImpossibleSizeException
     *          (the exception suggests an alternative dimension that is
     *          supported by this).
     */
    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        checkRep();
        if((dimension != null) && (dimension.getHeight() >= 0.0) && (dimension.getWidth()) >= 0.0) {
            ovalSize.setSize((double)dimension.getWidth(), (double)dimension.getHeight());
        } else {
            throw new ImpossibleSizeException();
        }
        checkRep();
    }

    /**
     * @effects Returns the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds() {
        checkRep();
        return new Rectangle(getLocation(), ovalSize);
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g) {
        checkRep();

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(getColor());
        g2d.fillOval(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                (int)ovalSize.getWidth(),
                (int)ovalSize.getHeight());

        checkRep();
    }

    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
        checkRep();

        LocationChangingOval clonedOval = null;

        clonedOval = (LocationChangingOval) super.clone();

        clonedOval.ovalSize = this.ovalSize;

        checkRep();
        return clonedOval;
    }

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is violated.
     */
    private void checkRep() {
        assert ovalSize != null : "Bound of LocationChangingOval is null";
        assert ovalSize.getHeight() >= 0 && ovalSize.getWidth() >= 0 : "Size of LocationChangingOval is negative";
    }

}
