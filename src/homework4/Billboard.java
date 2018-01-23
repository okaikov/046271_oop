package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
@SuppressWarnings("serial")
public class Billboard extends JFrame implements ActionListener {

    // preferred frame width and height.
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    // preferred minimal and maximal factors of window width and height for size of the shapes
    private static final int PANEL_SIZE = 20;
    private static final int NUM_PANELS_IN_ROW = 5;
    private static final int NUM_PANELS_IN_COL = 5;

    private static final int MAX_COLOR = 255;

    // graphical components
    private JMenuBar menuBar;
    private JMenu fileMenu, scheduleMenu, helpMenu;
    private JMenuItem exitItem,
            oneByOneItem, byColsItem,
            oddEvenItem, randomItem, aboutItem;
    private JCheckBoxMenuItem activeCheckItem;
    private JPanel mainPanel;


    // shapes that have been added to this
    private List<SquarePanel> panels = new ArrayList<>();

    // reference to ColorGenerator
    private ColorGenerator colorGen = ColorGenerator.getInstance();


    /**
     * @modifies this
     * @effects Initializes the GUI and enables a timer that steps animation
     *          of all shapes in this 25 times per second while animation
     *          checkbox is selected.
     */
    public Billboard() {
        super("Billboard");

        // create main panel and menubar
        mainPanel = (JPanel)createMainPanel();
        getContentPane().add(mainPanel);
        menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);

        int xOffset = WINDOW_WIDTH / 2 - PANEL_SIZE * NUM_PANELS_IN_ROW / 2;
        int yOffset = WINDOW_HEIGHT / 2 - PANEL_SIZE * NUM_PANELS_IN_COL / 2;

        for(int row = 0; row < NUM_PANELS_IN_ROW; row++) {
            for(int col = 0; col < NUM_PANELS_IN_COL; col++) {
                SquarePanel panel = new SquarePanel(new Point(xOffset + col * PANEL_SIZE, yOffset + row * PANEL_SIZE), Color.WHITE, PANEL_SIZE);
                panels.add(panel);
                colorGen.addListener(panel);
            }
        }

        // enable color change timer (ticks each two seconds)
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // calculate the bounds rectangle
                if (activeCheckItem.isSelected()) {
                    // Make one color change
                    colorGen.changeColor(getContentPane().getGraphics());

                    //repaint();  // make sure that the panels are redrawn
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
        activeCheckItem = new JCheckBoxMenuItem("Activate");
        fileMenu.add(activeCheckItem);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);




        scheduleMenu = new JMenu("Schedule");
        oneByOneItem = new JMenuItem("One by one");
        oneByOneItem.addActionListener(this);
        scheduleMenu.add(oneByOneItem);
        byColsItem = new JMenuItem("By columns");
        byColsItem.addActionListener(this);
        scheduleMenu.add(byColsItem);
        oddEvenItem = new JMenuItem("Odd-Even");
        oddEvenItem.addActionListener(this);
        scheduleMenu.add(oddEvenItem);
        randomItem = new JMenuItem("Random");
        randomItem.addActionListener(this);
        scheduleMenu.add(randomItem);
        menuBar.add(scheduleMenu);

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

        Iterator<SquarePanel> iter = panels.iterator();
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

        // File->Exit: close application
        if (source.equals(exitItem)) {
            dispose();
        }
        // Help->About : show about message dialog
        else if (source.equals(aboutItem)){
            JOptionPane.showMessageDialog(
                    this,
                    "Billboard - 4th homework assignment\n" +
                            "           Constantine Vainstein\n" +
                            "                             &\n" +
                            "                    Oren Kaikov",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // Change schedule of color
        else {
            if (source.equals(oneByOneItem)){
                colorGen.setScheduler(new OneByOne(NUM_PANELS_IN_ROW,NUM_PANELS_IN_COL));
            }
            else if (source.equals(byColsItem)) {
                colorGen.setScheduler(new ByColumns(NUM_PANELS_IN_ROW,NUM_PANELS_IN_COL));
            }
            else if (source.equals(oddEvenItem)) {
                colorGen.setScheduler(new OddEven(NUM_PANELS_IN_ROW,NUM_PANELS_IN_COL));
            }
            else if (source.equals(randomItem)) {
                colorGen.setScheduler(new Random(NUM_PANELS_IN_ROW,NUM_PANELS_IN_COL));
            }

            repaint();
        }
    }


    /**
     * @effects Animator application.
     */
    public static void main(String[] args) {
        Billboard application = new Billboard();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
    }
}
