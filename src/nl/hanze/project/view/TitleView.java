package nl.hanze.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Casper Scholte-Albers & Willem Johan Schuringa
 * @version 0.3
 */
public class TitleView extends JPanel{
    private JLabel title;

    public TitleView() {
        /**
         * Bepaald de gewenste voorkeur van het veld binnen de simulator.
         * Als de inhoud van de view niet tot deze dimensies gaat, zal het geforceerd worden.
         */
        Dimension dim = new Dimension();
        dim.setSize(300,50);
        setPreferredSize(dim);

        /**
         * Maakt een label aan en stelt de achtergrond kleur van de view in op Lightgrijs.
         */
        title = new JLabel("City Parking Groningen");
        setForeground(Color.WHITE);
        setBackground(Color.lightGray);

        add(title);

    }
}