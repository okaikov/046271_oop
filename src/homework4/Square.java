package homework4;

import java.awt.*;


/**
 * A Square is a Shape with four sides and right angles. All the sides are of the same length
 * A typical Square consists of the following set of
 * properties: {location, color, size}
 * The color property represents the first color of the shape, when it is created.
 * The size property represents the length of the sides.
 */
public class Square extends Shape{

    // Abstraction Function: Represents square.

    // Representation Invariant: size > 0

    private int size;
    private final int DEFAULT_SIZE = 10;


    /**
     * @effects Initializes this with a a given location, color and size.
     *          If the size is not positive, uses DEFAULT_SIZE.
     */
    public Square(Point location, Color color, int size) {
        super(location, color);
        if (size > 0) {
            this.size = size;
        } else {
            this.size = DEFAULT_SIZE;
        }

    }

    /**
     * @effects Returns the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds() {
        checkRep();
        return new Rectangle(getLocation(), new Dimension(size, size));
    }

    /**
     * @modifies g
     * @effects Draws filled square onto g.
     *          if g is null-reference throws AsserionError.
     */
    @Override
    public void draw(Graphics g) {
        checkRep();
        assert g != null : "g (graphics) is null-reference.";
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(getColor());
        g2d.fillRect(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                size,
                size);
        // Draw the border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                size,
                size);

        checkRep();
    }

    private void checkRep(){
        assert size > 0 : "Invalid size";
    }
}
