package nl.hanze.project.controller;

import nl.hanze.project.view.TitleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Willem Johan Schuringa
 * @version 0.3
 */
public class HistogramSettingGUI extends JPanel {
    public HistogramSettingGUI() {
        /**
         * Panel settings
         */
        setBackground(Color.GRAY);

        // Tabbed panel
        JTabbedPane tabbedPane = new JTabbedPane(); // initialiseert & maakt een tab-menu
        tabbedPane.addTab("Histogram Bezetting", new TitleView());
        tabbedPane.addTab("Histogram Winst", new TitleView());

        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        add(tabbedPane);
    }
}