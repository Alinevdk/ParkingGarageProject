package nl.hanze.project.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

/**
 * Credit:
 * https://www.youtube.com/watch?v=tOO2Fxuq7-g
 * @author Willem Johan Schuringa
 * @version 0.3
 */
public class SimulationSettingGUI extends JPanel implements ActionListener{
    private JLabel title;
    private JSlider slider;
    private JSlider slider1;

    public SimulationSettingGUI() {
        Box box = Box.createVerticalBox();
        add(box);

        title = new JLabel("Current value: 0");
        box.add(title);

        slider = new JSlider(JSlider.HORIZONTAL,0,20,0);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setBackground(Color.lightGray);
        slider.setMinimum(0);
        slider.setMaximum(250);
        box.add(slider);
        //title.set();
        setBackground(Color.lightGray);

        title = new JLabel("Current value: 0");
        box.add(title);

        slider1 = new JSlider(JSlider.HORIZONTAL,0,20,0);
        slider1.setMajorTickSpacing(5);
        slider1.setPaintTicks(true);
        slider1.setBackground(Color.lightGray);
        slider1.setMinimum(0);
        slider1.setMaximum(250);
        box.add(slider1);
        //title.set();
        setBackground(Color.lightGray);

        event e = new event();
        slider1.addChangeListener(e);
    }
    public void actionPerformed(ActionEvent e) {

    }
    public class event implements ChangeListener {
        public void stateChanged(ChangeEvent e){
            int value = slider.getValue();
            title.setText("Current value: " + value);
        }
    }
}