package homework1;

import java.awt.*;

public class ImpossibleSizeException extends Exception {

    final private int defaultHeight = 10;
    final private int defaultWidth = 10;
    final public Dimension validDimension = new Dimension(defaultWidth, defaultHeight);

    public ImpossibleSizeException(Dimension dim){
        System.out.println("Not valid size.");
        //dim.setSize(defaultHeight, defaultWidth);
    }
}
