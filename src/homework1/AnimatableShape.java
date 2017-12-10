package homework1;

import java.awt.*;

/**
 * AnimatableShape is an object that has all the properties and behavior of Shape and the behavior of Animatable.
 * AnimatableShape is an object that has all the properties and behavior of Shape and the behavior of Animatable.
 */
public class AnimatableShape {

    // Abstraction Function: Represents an animatable shape, where the shape is expressed by this.shape of Shape class
    //                       and the behavior of animatable is expressed by this animatable of Animatable class.
    // Representation Invariant: anumatable, shape != null

    private Animatable animatable;
    private Shape shape;

    /**
     * @effects Initializes this with non-null object animatableShape of class that
     * extends Shape and Animatable.
     */
    public <T extends Shape & Animatable> AnimatableShape(T animatableShape) {
        this.animatable = animatableShape;
        this.shape = animatableShape;
        checkRep();
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public void draw(Graphics g){
        checkRep();
        shape.draw(g);
        checkRep();
    }

    /**
     * @modifies this
     * @effects Updates the state of this to the appropriate value for the
     *          next animation step. The argument bound indicates the area
     *          within which this is allowed to move.
     */
    public void step(Rectangle bound) {
        checkRep();
        animatable.step(bound);
        checkRep();
    }

    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        checkRep();
        return  shape.getLocation();
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     *          returns location after call has completed.
     */
    public void setLocation(Point location) {
        checkRep();
        shape.setLocation(location);
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
    public void setSize(Dimension dimension) throws ImpossibleSizeException{
        checkRep();
        shape.setSize(dimension);
        checkRep();
    }


    /**
     * @return the bounding rectangle of this.
     */
    public Rectangle getBounds(){
        checkRep();
        return shape.getBounds();
    }


    /**
     * @return true if the given point lies inside the bounding rectangle of
     *         this and false otherwise.
     */
    public boolean contains(Point point) {
        checkRep();
        return shape.contains(point);
    }


    /**
     * @return color of this.
     */
    public Color getColor() {
        checkRep();
        return shape.getColor();
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        checkRep();
        shape.setColor(color);
        checkRep();
    }



    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is violated.
     */
    private void checkRep() {
        assert shape != null:
                "shape is null";
        assert animatable != null:
                "animatable is null";
    }
}