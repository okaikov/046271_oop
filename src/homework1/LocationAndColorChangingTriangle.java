package homework1;

import java.awt.*;

public class LocationAndColorChangingTriangle extends LocationAndColorChangingShape{

    // TODO (BOM): Write Abstraction Function

    // TODO (BOM): Write Representation Invariant

    private Dimension triangleSize;

    public LocationAndColorChangingTriangle(Point location, Color color, Dimension dim) {
        super(location, color);

        assert location != null : "Null location";
        assert color != null : "Null color";
        assert dim != null : "Null dimension";

        triangleSize = new Dimension(0, 0);
        try {
            setSize(dim);
        }
        catch(ImpossibleSizeException e) {
            uncheckedSetSize(e.getDefaultSize());
        }
        checkRep();
    }

    private void uncheckedSetSize(Dimension dimension) {
        triangleSize.setSize(dimension);
    }

    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        checkRep();
        if((dimension.getHeight() >= 0.0) && (dimension.getWidth()) >= 0.0) {
            // The size has been checked, so it is safe to call for unchecked method.
            uncheckedSetSize(dimension);
        } else {
            throw new ImpossibleSizeException();
        }
        checkRep();
    }

    @Override
    public Rectangle getBounds() {
        checkRep();
        return new Rectangle(getLocation(), triangleSize);
    }

    @Override
    public void draw(Graphics g) {
        checkRep();
        final int NUM_OF_VERTICES_IN_TRIANGLE = 3;
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
        // TODO: Remove the drawing of bounding rectangle
        g2d.drawRect(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                (int)triangleSize.getWidth(),
                (int)triangleSize.getHeight());

        checkRep();
    }

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @throws AssertionError if representation invariant is violated.
     */
    private void checkRep() {
        assert triangleSize != null : "Bound is null";
    }
}
