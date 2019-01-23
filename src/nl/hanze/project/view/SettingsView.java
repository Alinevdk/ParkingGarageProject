package nl.hanze.project.view;

import nl.hanze.project.controller.TabController;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a screen to adjust the simulation  settings
 * @author Willem Johan Schuringa
 * @version 0.2
 */
public class SettingsView extends TabController
{
    public SettingsView() {
        // initialiseert & maakt een tab-menu
        tabbedPane.addTab("Histogram Bezetting", new TitleView());
        tabbedPane.addTab("Histogram Winst", new TitleView());
    }
}