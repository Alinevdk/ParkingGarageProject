package nl.hanze.project.view;

import nl.hanze.project.controller.SimulationSettingGUI;
import nl.hanze.project.controller.TabController;

/**
 * Creates a screen to adjust the simulation  settings
 * @author Willem Johan Schuringa
 * @version 0.2
 */
public class SettingsView extends TabController
{
    public SettingsView() {
        // initialiseert & maakt een tab-menu
        tabbedPane.addTab("Settings", new SimulationSettingGUI());
    }
}