package nl.hanze.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Casper Scholte-Albers
 * @version 0.3
 */
public class TitleView extends JPanel{
    private JLabel title;

    public TitleView() {
        title = new JLabel("City Parking Groningen");
        //title.set();
        setBackground(Color.lightGray);
        add(title);

    }
}