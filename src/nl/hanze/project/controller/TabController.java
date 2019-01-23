package nl.hanze.project.controller;

import javax.swing.*;
import java.awt.*;

/**
 * @author Willem Johan Schuringa
 * @version 0.3
 */
abstract public class TabController extends JPanel {
    protected JTabbedPane tabbedPane;
    public TabController() {
        /**
         * Panel settings
         */
        setBackground(Color.GRAY);
        /**
         * Panel initialization
         */
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        add(tabbedPane);

    }
}