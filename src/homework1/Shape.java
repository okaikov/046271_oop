package homework1;

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

    private Point location;
    private Color color;


    // Write Abstraction Function: The shape is represented by location of left upper corner of bounding rectangle and
    // color.

    // Representation Invariant: location != null, color != null


    /**
     * @effects Initializes this with a a given location and color.
     */
    public Shape(Point location, Color color) {
        setLocation(location);
        setColor(color);
        checkRep();
    }


    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        checkRep();
        return new Point(location);
        }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     *          returns location after call has completed.
     */
    public void setLocation(Point location) {
        checkRep();
        // TODO: check that location is not null
        this.location = (Point)location.clone();
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
    public abstract void setSize(Dimension dimension) throws ImpossibleSizeException;


    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();


    /**
     * @return true if the given point lies inside the bounding rectangle of
     *         this and false otherwise.
     */
    public boolean contains(Point point) {
        checkRep();
        return getBounds().contains(point);
    }


    /**
     * @return color of this.
     */
    public Color getColor() {
        checkRep();
        return color;
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        checkRep();
        this.color = color;
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
        checkRep();

        Shape clonedShape = null;
        /* As we override Object.clone(), syntactically we MUST handle the exception that can be thrown by Object.clone
        or throw it to the caller.
        We do not throw the exception to the caller because this class implements Cloneable and all the instances of
        the classes extending this class, will also be cloneable. */
        try {
            clonedShape = (Shape) super.clone();
        }
        catch(CloneNotSupportedException e) {
            // This exception should never be thrown by super, but just to be on the safe side...
            System.err.println("An attempt to clone a non-cloneable object was performed.");
            clonedShape = null;
            System.exit(1);
        }
        clonedShape.color = new Color(color.getRGB());
        clonedShape.location = (Point)location.clone();        checkRep();
        return clonedShape;
    }

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is violated.
     */
    protected void checkRep() {
        assert location != null:
            "location is null";
        assert color != null:
            "color is null";
    }
}
