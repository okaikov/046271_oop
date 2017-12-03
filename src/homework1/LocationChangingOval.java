package homework1;

import java.awt.*;

public class LocationChangingOval extends LocationChangingShape {

    private Dimension ovalSize;

    LocationChangingOval(Point location, Color color, Dimension dim) {
        super(location, color);

        assert location != null : "Null location";
        assert color != null : "Null color";
        assert dim != null : "Null dimension";

        ovalSize = new Dimension(0, 0);
        try {
            setSize(dim);
        }
        catch(ImpossibleSizeException e) {
            uncheckedSetSize(e.getDefaultSize());
        }
        checkRep();
    }

    private void uncheckedSetSize(Dimension dimension) {
        ovalSize.setSize(dimension);
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
        return new Rectangle(getLocation(), ovalSize);
    }

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
        // TODO: Remove the drawing of bounding rectangle
        g2d.drawRect(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                (int)ovalSize.getWidth(),
                (int)ovalSize.getHeight());

        checkRep();
    }

    @Override
    protected void checkRep() {
        super.checkRep();
        assert ovalSize != null : "Bound is null";
    }

}
