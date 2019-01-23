package nl.hanze.project.view;


import nl.hanze.project.controller.SimulationSettingGUI;
import nl.hanze.project.controller.TabController;
import nl.hanze.project.controller.Simulator;

import java.awt.*;

/**
 * Creates a screen to adjust the simulation  settings
 * @author Willem Johan Schuringa
 * @version 0.2
 */
public class ChartView extends TabController
{
    private GraphView graph;

    public ChartView() {

        this.graph = new GraphView(Simulator.getPopulation());

        // initialiseert & maakt een tab-menu
        tabbedPane.addTab("Settings", new TitleView());
        tabbedPane.addTab("PopulationGraph", graph);
        tabbedPane.setPreferredSize(new Dimension(300, 500));
    }
}