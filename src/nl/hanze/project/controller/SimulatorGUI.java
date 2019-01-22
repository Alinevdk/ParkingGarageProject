package nl.hanze.project.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Casper Scholte-Albers
 * @version 0.3
 */
public class SimulatorGUI extends JPanel implements ActionListener{
    private JButton stepOne;
    private JButton stepHundred;
    private JButton stop;
    private JButton start;

    public SimulatorGUI() {
        stepOne = new JButton("1 step");
        stepHundred = new JButton("100 steps");
        start = new JButton("Start");
        stop = new JButton("Stop");

        stepOne.setBounds(100, 150, 100, 30);
        stepOne.addActionListener(this);

        stepHundred.setBounds(210, 150, 100, 30);
        stepHundred.addActionListener(this);

        start.setBounds(320, 150, 100, 30);
        start.addActionListener(this);

        stop.setBounds(430, 150, 100, 30);
        stop.addActionListener(this);

        add(stepOne);
        add(stepHundred);
        add(start);
        add(stop);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stepOne) {
            JOptionPane.showMessageDialog(null, "test1");
            //tick()
        }
        if (e.getSource() == stepHundred){
            JOptionPane.showMessageDialog(null, "test100");
            /*
            int step = 0;
            while(step <= 100){
                tick();
            }
             */
        }
        if (e.getSource() == start){
            JOptionPane.showMessageDialog(null, "testStart");
        }
        if (e.getSource() == stop){
            JOptionPane.showMessageDialog(null, "testStop");
            //suspend();
        }
    }
}