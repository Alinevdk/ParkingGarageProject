/**
 * In deze klasse word de informatie over Reserveringen gedefinieerd
 *
 * @author w.j. schuringa & l.g. caspers
 * @version 1.0 1/17/2019
 */


package nl.hanze.project.model;

import java.awt.*;
import java.util.Random;

public class ReservedCar extends Car {
    private static final Color COLOR = Color.yellow;
    private Reservation reservation;

    public ReservedCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        //    reservation = new Reservation(1919);
    }

    public Color getColor() {
        return COLOR;
    }


}
