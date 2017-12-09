package homework1;

import java.awt.*;


/**
 * AngleChangingSector is an Animatable Shape that is a sector of an oval.
 * The animation is increasing the sector angle till 359 degrees, and then decreasing it to 0 degrees alternately.
 * The properties of AngleChangingSector are {location, color, size, startAngle, sectorAngle},
 * where startAngle is the angle between the horizontal axis reference and the start of the sector,
 * and the sectorAngle is the angle between the start angle of the sector and the end angle of the sector when the
 * shape is created.
 */
public class AngleChangingSector extends Shape implements Animatable{
    // Abstraction Function: Represents a filled sector of size ovalSize (width and height), start angle startAngle and
    //                       angle of sector represented by currentSectorAngle. The shape animation is represented by
    //                       a boolean flag "angleIncreasing" to indicate in which state (increasing or decreasing)
    //                       currently the animated shape is.
    //
    // Representation Invariant: 0.0 <= startAngle < 360.0
    //                           0.0 <= currentSectorAngle < 360.0
    //                           ovalSize != null

    final double MIN_ANGLE_DEG_INCLUDED = 0.0;
    final double MAX_ANGLE_DEG_EXCLUDED = 360.0;

    final double MIN_ANIMATION_ANGLE_DEG = 0.0;
    final double MAX_ANIMATION_ANGLE_DEG = 359.0;
    final double ANIMATION_STEP_DEG = 1.0;

    private double currentSectorAngle;
    private double startAngle;
    private Dimension ovalSize;
    private boolean angleIncreasing;

    public AngleChangingSector(Point location, Color color, Dimension dim, double startAngle, double sectorAngle) {
        super(location, color);

        assert location != null : "Null location";
        assert color != null : "Null color";
        assert dim != null : "Null dimension";
        assert sectorAngle >= MIN_ANGLE_DEG_INCLUDED : "Invalid angle";
        assert sectorAngle < MAX_ANGLE_DEG_EXCLUDED : "Invalid angle";
        assert startAngle >= MIN_ANGLE_DEG_INCLUDED : "Invalid angle";
        assert startAngle < MAX_ANGLE_DEG_EXCLUDED : "Invalid angle";
        assert dim.getWidth() >= 0.0 : "Invalid width";
        assert dim.getHeight() >= 0.0 : "Invalid height";

        ovalSize = new Dimension(dim);
        this.currentSectorAngle = sectorAngle;
        this.startAngle = startAngle;
        angleIncreasing = true;
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
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        checkRep();
        if((dimension.getHeight() >= 0.0) && (dimension.getWidth()) >= 0.0) {
            ovalSize.setSize(dimension);
        } else {
            throw new ImpossibleSizeException();
        }
        checkRep();
    }


    /**
     * @return the bounding rectangle of this.
     */
    public Rectangle getBounds()
    {
        checkRep();
        return new Rectangle(getLocation(), ovalSize);
    }


    /**
     * @modifies this
     * @effects Updates the state of this (currentAngle in angleIncreasing) to the appropriate value for the
     *          next animation step. The argument bound indicates the area
     *          within which this is allowed to move.
     */
    public void step(Rectangle bound){
        checkRep();
        if(angleIncreasing){
            currentSectorAngle += ANIMATION_STEP_DEG;
        } else {
            currentSectorAngle -= ANIMATION_STEP_DEG;
        }

        if(currentSectorAngle > MAX_ANIMATION_ANGLE_DEG) {
            currentSectorAngle -= ANIMATION_STEP_DEG;
            angleIncreasing = false;
        }
        else if(currentSectorAngle < MIN_ANIMATION_ANGLE_DEG) {
            currentSectorAngle += ANIMATION_STEP_DEG;
            angleIncreasing = true;
        }
        checkRep();

    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     *          if g is null-reference throws AsserionError;
     */
    public void draw(Graphics g){
        checkRep();

        assert g != null : "Graphics is null-reference.";

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(getColor());
        g2d.fillArc(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                (int)ovalSize.getWidth(),
                (int)ovalSize.getHeight(),
                (int)startAngle,
                (int)currentSectorAngle
        );
        checkRep();
    }

    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
        checkRep();

        AngleChangingSector clonedSector = null;

        clonedSector = (AngleChangingSector) super.clone();

        clonedSector.currentSectorAngle = this.currentSectorAngle;
        clonedSector.startAngle = this.startAngle;
        clonedSector.angleIncreasing = this.angleIncreasing;
        clonedSector.ovalSize = new Dimension(this.ovalSize);

        checkRep();
        return clonedSector;
    }

    /**
     * Checks to see if the representation invariant is being
     * violated.
     * @effects throws AssertionError if representation invariant is violated.
     */
    private void checkRep(){
        assert (0.0 <= startAngle) && (startAngle < 360.0) : "startAngle is out of rangle";
        assert (0.0 <= currentSectorAngle) && (currentSectorAngle < 360.0) : "currentSectorAngle is out of range";
        assert ovalSize != null : "The size f sector is null-reference";
    }
}
