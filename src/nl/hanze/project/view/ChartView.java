package nl.hanze.project.view;

import nl.hanze.project.controller.SimulationSettingGUI;
import nl.hanze.project.controller.TabController;

/**
 * Creates a screen to adjust the simulation  settings
 * @author Willem Johan Schuringa
 * @version 0.2
 */
public class ChartView extends TabController
{
    public ChartView() {
        // initialiseert & maakt een tab-menu
        tabbedPane.addTab("Settings", new TitleView());
    }
}