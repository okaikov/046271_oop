package homework4;

import java.awt.*;

/**
 * A Panel is an abstraction of billboard panel that can change it's color and draw itself.
 */
public interface Panel {
    /**
     * @modifies this, g
     * @effects Changes the color of the panel and redraws. If g is null, shall not redraw. If color is null,
     * the color shall not be changed.
     */
    public void notifyNewColor(Graphics g, Color color);

    /**
     * @modifies this, g
     * @effects draws itself in g. If g is null, shall not redraw.
     */
    public void draw(Graphics g);
}
