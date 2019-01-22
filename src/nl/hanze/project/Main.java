package nl.hanze.project;

import nl.hanze.project.controller.Simulator;
import nl.hanze.project.model.*;
import nl.hanze.project.view.*;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args)
    {

        Simulator sim = new Simulator();
        sim.run();
    }
}