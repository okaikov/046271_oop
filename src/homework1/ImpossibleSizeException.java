package homework1;

import java.awt.*;

public class ImpossibleSizeException extends Exception {

    final private double defaultHeight = 10;
    final private double defaultWidth = 10;

    public ImpossibleSizeException(Dimension dim){
        System.out.println("Not valid size.");
        dim.setSize(defaultHeight, defaultWidth);
    }
}
