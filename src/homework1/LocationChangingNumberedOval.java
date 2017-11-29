package homework1;

import java.awt.*;

public class LocationChangingNumberedOval extends LocationChangingOval {

    static final int INITIAL_SERIAL_NUM = 1;
    static int ovalsCounter = INITIAL_SERIAL_NUM;

    int serialNum;

    public LocationChangingNumberedOval(Point location, Color color, Dimension dim) {
        super(location, color, dim);
        serialNum = ovalsCounter;
        ovalsCounter++;

        checkRep();
    }

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

    protected void checkRep() {
        super.checkRep();
        assert serialNum >= 1 : "Serial number of Numbered Oval is illegal";
    }
}
