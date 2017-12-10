package homework1;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
@SuppressWarnings("serial")
public class Animator extends JFrame implements ActionListener {

    // preferred frame width and height.
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    // graphical components
    private JMenuBar menuBar;
    private JMenu fileMenu, insertMenu, helpMenu;
    private JMenuItem newItem, exitItem,
                        triangleItem, ovalItem,
                        numberedOvalItem, sectorItem, aboutItem;
    private JCheckBoxMenuItem animationCheckItem;
    private JPanel mainPanel;


    // shapes that have been added to this
    private List<AnimatableShape> shapes = new ArrayList<>();


    /**
     * @modifies this
     * @effects Initializes the GUI and enables a timer that steps animation
     *          of all shapes in this 25 times per second while animation
     *          checkbox is selected.
     */
    public Animator() {
        super("Animator");

        // create main panel and menubar
        mainPanel = (JPanel)createMainPanel();
        getContentPane().add(mainPanel);
        menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);
        // calculate the bounds rectangle
        pack();
        /*Rectangle r = getContentPane().getGraphics().getClipBounds();*/
        Rectangle bounds = new Rectangle(/*r*/new Rectangle(
                getContentPane().getX(),
                getContentPane().getY() + menuBar.getHeight(),
                getContentPane().getWidth(),
                getContentPane().getHeight() - menuBar.getHeight())
        );

        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (animationCheckItem.isSelected()) {
                    // Make one animation step for all shapes in this
                    for(AnimatableShape s : shapes) {
                        s.step(bounds);
                    }

                    repaint();  // make sure that the shapes are redrawn
                }
            }
        });
        timer.start();
    }


    /**
     * @return main GUI panel.
     */
    private JComponent createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(
                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainPanel.setBackground(Color.WHITE);

        return mainPanel;
    }


    /**
     * @return main GUI menubar.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        newItem.addActionListener(this);
        fileMenu.add(newItem);
        animationCheckItem = new JCheckBoxMenuItem("Animation");
        fileMenu.add(animationCheckItem);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        insertMenu = new JMenu("Insert");
        triangleItem = new JMenuItem("Triangle");
        triangleItem.addActionListener(this);
        insertMenu.add(triangleItem);
        ovalItem = new JMenuItem("Oval");
        ovalItem.addActionListener(this);
        insertMenu.add(ovalItem);
        numberedOvalItem = new JMenuItem("Numbered Oval");
        numberedOvalItem.addActionListener(this);
        insertMenu.add(numberedOvalItem);
        sectorItem = new JMenuItem("Sector");
        sectorItem.addActionListener(this);
        insertMenu.add(sectorItem);
        menuBar.add(insertMenu);

        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(this);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        return menuBar;
    }


    /**
     * @modifies g
     * @effects Paint this including all its shapes to g. This method is
     *          invoked by Swing to draw components. It should not be invoked
     *          directly, but the repaint method should be used instead in
     *          order to schedule the component for redrawing.
     */
    public void paint(Graphics g) {
        super.paint(g);

        Graphics drawArea;

        Iterator<AnimatableShape> iter = shapes.iterator();
        drawArea = getContentPane().getGraphics();
        while (iter.hasNext()) {
            iter.next().draw(drawArea);
        }
    }


    /**
     * @modifies this
     * @effects Invoked when the user selects an action from the menubar
     *          and performs the appropriate operation.
     */
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());

        // File->New : clear all shapes
        if (source.equals(newItem)) {
            shapes.clear();
            repaint();

            //TODO (BOM):  Add code for number of LocationChangingNumerOval = 0
        }

        // File->Exit: close application
        else if (source.equals(exitItem)) {
            dispose();
        }
        // Help->About : show about message dialog
        else if (source.equals(aboutItem)){
            JOptionPane.showMessageDialog(
                    this,
                    "Animator - 1st" +
                            " homework assignment",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // Insert a shape
        else {
            if (source.equals(numberedOvalItem)){

                LocationChangingNumberedOval numbOval = new LocationChangingNumberedOval(new Point(100, 100), Color.RED, new Dimension(100, 60));
                AnimatableShape numbOvalRef = new AnimatableShape(numbOval);
                shapes.add(numbOvalRef);
                // TODO (BOM): Add code for creating the appropriate shape such that:
                //       it is completely inside the window's bounds &&
                //       its location, size and color are randomly selected &&
                //       1/10*WINDOW_WIDTH <= shape.width < 3/10*WINDOW_WIDTH &&
                //       1/10*WINDOW_HEIGHT <= shape.height < 3/10*WINDOW_HEIGHT

            }
            else if (source.equals(triangleItem)) {
                LocationAndColorChangingTriangle triangle = new LocationAndColorChangingTriangle(new Point(200, 200), Color.BLUE, new Dimension(60, 100));
                AnimatableShape triangleRef = new AnimatableShape(triangle);
                shapes.add(triangleRef);
            }
            else if (source.equals(ovalItem)) {
                LocationChangingOval oval = new LocationChangingOval(new Point(100, 200), Color.GREEN, new Dimension(80, 60));
                AnimatableShape ovalRef = new AnimatableShape(oval);
                shapes.add(ovalRef);
            }
            else if (source.equals(sectorItem)) {
                AngleChangingSector sector = new AngleChangingSector(new Point(200, 100), Color.PINK, new Dimension(80, 60), 0.0, 30.0);
                AnimatableShape sectorRef = new AnimatableShape(sector);
                shapes.add(sectorRef);
            }

            repaint();
        }


    }


    /**
     * @effects Animator application.
     */
    public static void main(String[] args) {
        Animator application = new Animator();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
    }
}
