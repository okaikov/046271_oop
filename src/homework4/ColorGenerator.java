package homework4;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * ColorGenerator is an abstraction of object that receives a list of listeners, then
 * upon command randomly selects a color and notifies all the listeners about the new color.
 * The listeners are notified according to certain order that may be set by {@code setScheduler()}.
 */
public class ColorGenerator {
    // Abstraction Function: Represents an object that upon message, decides on color change and notifies all the
    // listeners according to certain order, specified by the scheduler.

    // Representation Invariant: {@code scheduler != null && color != null && panels != null}

    private static ColorGenerator instance = new ColorGenerator();

    private static final int TIME_DELAY_BETWEEN_NOTIFICATIONS_ms = 40;

    // scheduler defines the order, by which the listeners will be notified about a new color.
    private Scheduler scheduler = new OneByOne(5,5);

    private Color color = Color.BLACK;

    // list of listeners (in this case, panels).
    private ArrayList<Panel> panels = new ArrayList<>();

    /**
     * @effects: Returns the unique instance of this. (Singleton)
     */
    public static ColorGenerator getInstance() {
        return instance;
    }

    /**
     * The c'tor is "hidden" from the user in order to be able to create additional instances of this class. (Singleton)
     */
    private ColorGenerator() {
    }

    /**
     * @effects Choose a random color and notifies all the panels about new color. The panels are notified in order,
     * specified by {@code setScheduler()} with time delay of TIME_DELAY_BETWEEN_NOTIFICATIONS_ms between each
     * notification.
     *
     * @modifies this
     */
    public void changeColor(Graphics g) {
        // No need to check g for null pointer. We transmit it as is to the panel, and the panel must know how
        // to handle it.
        checkRep();
        java.util.Random rnd = new java.util.Random();
        color = new Color(rnd.nextInt());
        scheduler.reset();
        while (scheduler.hasNext()) {
            panels.get(scheduler.getNext()).notifyNewColor(g, color);
            try {
                TimeUnit.MILLISECONDS.sleep(TIME_DELAY_BETWEEN_NOTIFICATIONS_ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        checkRep();
    }

    /**
     * @effects Change the schedule of the notifications to the panels about the new color. Reset the scheduler.
     * @modofies this.
     */
    public void setScheduler(Scheduler s) {
        if (s == null) {
            System.err.println("The Scheduler (s) is a null reference.");
            return;
        }
        checkRep();
        this.scheduler = s;
        s.reset();
        checkRep();
    }

    /**
     * @effects Add an additional panel (listener) to be notified when the color changes.
     * @modofies this.
     */
    public void addListener(Panel panel){
        panels.add(panel);
    }

    /**
     * Checks the representation invariant. If it is violated, throws AssertionError.
     */
    private void checkRep(){
        assert scheduler != null : "scheduler is a null reference";
        assert color != null : "color is a null reference";
        assert panels != null : "panels is a null reference";
    }
}
