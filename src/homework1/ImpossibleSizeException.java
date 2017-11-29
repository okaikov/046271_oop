package homework1;

import java.awt.*;

/**
 * ImpossibleSizeException is an Exception caused by invalid size. The exception provides a default valid size by using
 * the method getDefaulSize.
 */
public class ImpossibleSizeException extends Exception {

    private final int DEFAULT_HEIGHT = 10;
    private final int DEFAULT_WIDTH = 10;
    public final Dimension validDimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    /**
     * Currently nothing will happen if ImpossibleSizeException is thrown, but the object of exception can be quired
     * for default valid size.
     */
    public ImpossibleSizeException(){
    }

    /**
     *
     * @effects Returns a valid default dimension.
     */
    public final Dimension getDefaultSize(){
        return validDimension;
    }
}
