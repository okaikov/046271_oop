package homework4;

import java.awt.*;

/**
 * SquarePanel is an abstraction of billboard panel of square shape.
 */
public class SquarePanel implements Panel{

    // Abstraction Function: SqaurePanel represents a billboard panel of square shape. The graphical object sqaure is
    // represented by square member of Square class.

    // Representation invariant: square != null

    private Square square;

    /**
     * @modifies this
     * @effects Creates a square billboard panel, located in {@code location} of color {@code color} and size {@code size}
     */
    public SquarePanel(Point location, Color color, int size){
        // The size is not checked intentionally, because square should know how to handle it.
        square = new Square(location, color, size);
        checkRep();
    }

    /**
     * @modifies this, g
     * @effects Changes the color of the panel and redraws. If g is null, shall not redraw. If color is null,
     * the color shall not be changed.
     */
    @Override
    public void notifyNewColor(Graphics g, Color color) {
        checkRep();
        if (color != null) {
            square.setColor(color);
        }
        if (g != null) {
            draw(g);
        }
        checkRep();
    }

    /**
     * @modifies this, g
     * @effects draws itself in g. If g is null, shall not redraw.
     */
    @Override
    public void draw(Graphics g) {
        checkRep();
        if (g != null) {
            square.draw(g);
        }
        checkRep();
    }

    /**
     * Checks the representation invariant. If it is violated, throws AssertionError.
     */
    private void checkRep(){
        assert square != null : "Square is a null reference.";
    }
}
