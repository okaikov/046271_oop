package homework1;

import java.awt.*;

/**
 * A LocationAndColorChangingTriangle is a LocationAndColorChangingShape of type triangle.
 * It has the following set of properties: {location, color, shape, size, velocity}
 */
public class LocationAndColorChangingTriangle extends LocationAndColorChangingShape{

    // Abstraction Function: Represents right angle triangle, which location, color and size can be changed.
    //                       The right angle of the triangle is on the upper-left corner of the bounding rectangle,
    //                       and its catheti are on the upper and left sides of the bounding rectangle.
    //                       The location and the color are represented by the base class.
    //                       The size is represented by the size of the bounding rectangle represented by triangleSize
    //                       object of Dimension class.

    // Representation Invariant: (triangleSize != null) AND (triangleSize.width,triangleSize.height >= 0)

    private Dimension triangleSize;

    /**
     * @effects Initializes this with a a given location, color and size (dim).
     *
     */
    public LocationAndColorChangingTriangle(Point location, Color color, Dimension dim) {
        super(location, color);

        // Check input parameters except for base-class parameters.
        assert dim != null : "LocationAndColorChangingTriangle was created with null-reference dimension";
        assert dim.getHeight() >= 0 && dim.getWidth() >= 0 : "LocationAndColorChangingTriangle was created with negative size";

        triangleSize = new Dimension(dim);

        checkRep();
    }


    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     *          dimension.
     *          If this cannot be resized to the specified dimension =>
     *          this is not modified, throws ImpossibleSizeException
     *          (the exception suggests an alternative dimension that is
     *           supported by this).
     */
    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        checkRep();
        if((dimension.getHeight() >= 0.0) && (dimension.getWidth()) >= 0.0) {
            // The size has been checked, so it is safe to call for unchecked method.
            triangleSize.setSize(dimension);
        } else {
            throw new ImpossibleSizeException();
        }
        checkRep();
    }

    /**
     * @effects return the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds() {
        checkRep();
        return new Rectangle(getLocation(), triangleSize);
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     *          if g is null-reference throws AsserionError
     */
    @Override
    public void draw(Graphics g) {
        checkRep();
        final int NUM_OF_VERTICES_IN_TRIANGLE = 3;

        assert g != null : "g (Graphics) is a null-reference.";

        int [] verticesX = {
                (int)(getBounds().getMinX()),
                (int)(getBounds().getMaxX()),
                (int)(getBounds().getMinX()) };
        int [] verticesY = {
                (int)(getBounds().getMinY()),
                (int)(getBounds().getMinY()),
                (int)(getBounds().getMaxY()) };

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(getColor());
        g2d.fillPolygon(verticesX, verticesY, NUM_OF_VERTICES_IN_TRIANGLE);

        checkRep();
    }

    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
        checkRep();

        LocationAndColorChangingTriangle clonedTriangle = null;

        clonedTriangle = (LocationAndColorChangingTriangle) super.clone();

        clonedTriangle.triangleSize = new Dimension(this.triangleSize);

        checkRep();
        return clonedTriangle;
    }

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @effects throws AssertionError if representation invariant is violated.
     */
    private void checkRep() {
        assert triangleSize != null : "Bound is null";
        assert triangleSize.getHeight() >= 0 && triangleSize.getWidth() >= 0 :
                "LocationAndColorChangingTriangle size is invalid";
    }
}
