/**
 * In deze klasse word de informatie over Reserveringen gedefinieerd
 *
 * @author w.j. schuringa & l.g. caspers
 * @version 1.0 1/17/2019
 */

package nl.hanze.project.model;

import nl.hanze.project.Main;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import nl.hanze.project.controller.Simulator;

public class Reservation extends Car{
    private Location location;
    private int startTime;            // tijd waarvoor de auto reserveerd
    private int arrivalTime;          // aankomst tijd
    private int endTime;              // vertrek tijd

    private int startTimeSlotDay;
    private int startTimeSlotHour;
    private int startTimeSlotMinute;
    private int endTimeSlotDay;
    private int endTimeSlotHour;
    private int endTimeSlotMinute;

    private int day;
    private int hour;
    private int minute;

    private static final double ARRIVALCHANCE = 0.20;

    public static List<Reservation> reservations = new ArrayList<>();


    private static final Color COLOR=Color.green;   // stelt de kleur in van het veld voor de simulatie

    /**
     * Constructor voor een Reservation
     * @param day bevat de start tijd voor de reservering
     */
    public Reservation(Location location, int day, int hour, int minute) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        setLocation(location);
        createTimeSlot();
        reservations.add(this);
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }

    public Color getColor(){
        return COLOR;
    }

    //public void tickRes(int minute){
    public boolean tickRes(int minute){
        //Simulator sim = new Simulator();
        if(isInTimeSlot(minute)){
            Random random = new Random();
            if(random.nextDouble() <= ARRIVALCHANCE){
               // ReservedCar reservedCar = new ReservedCar();
                return true;
            //    Simulator.reservations.add(reservedCar);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Creert een tijdsslot voor hoe lang de plek wordt gereserveerd in de garage
     * @author w.j.schuringa & l.g.caspers
     */
    private void createTimeSlot(){
        if (minute < 15) {
            int remainder = 15 - minute;
            startTimeSlotMinute = 60 - remainder;
            endTimeSlotMinute = minute + 30;

            // controleert of het gereserveerde uur kleiner is dan 1
            if (hour < 1) {
                startTimeSlotHour = 23;
                // controleert of de gereserveerde dag kleiner is dan 1
                if (day < 1) {
                    startTimeSlotDay = 7;
                } else {
                    startTimeSlotDay = day - 1;
                }

            } else {
                startTimeSlotHour = hour - 1;
            }
        } else {
            startTimeSlotMinute = minute - 15;
            startTimeSlotHour = hour;
            startTimeSlotDay = day;

            if((minute + 30) > 59) {
                if((hour + 1) > 23){
                    if((day + 1) > 6){
                        int remainder = (minute + 30) - 60;
                        endTimeSlotMinute = remainder;
                        endTimeSlotHour = 0;
                        endTimeSlotDay = 0;
                    } else {
                        int remainder = (minute + 30) - 60;
                        endTimeSlotMinute = remainder;
                        endTimeSlotHour = 0;
                        endTimeSlotDay = startTimeSlotDay + 1;
                    }
                } else {
                    int remainder = (minute + 30) - 60;
                    endTimeSlotMinute = remainder;
                    endTimeSlotHour = startTimeSlotHour + 1;
                    endTimeSlotDay = startTimeSlotDay;
                }
            } else {
                endTimeSlotMinute = minute + 30;
                endTimeSlotHour = startTimeSlotHour;
                endTimeSlotDay = startTimeSlotDay;
            }
        }
    }

    /**
     * Controleert of de auto in het tijdslot zit
     * @author l.g.caspers & w.j.schuringa
     */
    private boolean isInTimeSlot(int minute){
        if((minute >= startTimeSlotMinute) && (startTimeSlotMinute + endTimeSlotMinute) <= (minute + startTimeSlotMinute)) {
            return true;
        }
        return false;
    }
}
