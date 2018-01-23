package homework4;

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape {

    private Point location;
    private Color color;


    // Abstraction Function: Represents a shape, bounded by rectangle with left upper corner placed in this.location
    // and of color this.color.

    // Representation Invariant: location != null, color != null


    /**
     * @effects Initializes this with a a given location and color.
     */
    public Shape(Point location, Color color) {
        // Check input parameteres.
        assert location != null : "A shape was created with null-reference location";
        assert color != null : "A shape was created with null-reference color";

        this.location = (Point)location.clone();
        this.color = color;
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
        this.location = (Point)location.clone();
        checkRep();
    }


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
        checkRep();
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is violated.
     */
    private void checkRep() {
        assert location != null:
            "location is null";
        assert color != null:
            "color is null";
    }
}
