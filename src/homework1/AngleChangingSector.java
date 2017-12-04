package homework1;

import java.awt.*;

public class AngleChangingSector extends Shape implements Animatable{
    // TODO: Abstraction Function
    // TODO: Representation Invariant

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
        // TODO: Something is strange with the exception thing. The message in the forum about not usung methods in c'tor misleads.
        if((dimension.getHeight() >= 0.0) && (dimension.getWidth()) >= 0.0) {
            // The size has been checked, so it is safe to call for unchecked method.
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
     * @effects Updates the state of this to the appropriate value for the
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
        // TODO: check bounds (sinuses and cosinuses probably)
        checkRep();

    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public void draw(Graphics g){
        checkRep();

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
        // TODO: Remove the drawing of bounding rectangle
        g2d.drawRect(
                (int)getLocation().getX(),
                (int)getLocation().getY(),
                (int)ovalSize.getWidth(),
                (int)ovalSize.getHeight());

        checkRep();
    }

    private void checkRep(){
        // TODO
    }
}
