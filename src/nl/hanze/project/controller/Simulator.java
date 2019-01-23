package nl.hanze.project.controller;

//import nl.hanze.project.model.CarQueue;
//import nl.hanze.project.model.AdHocCar;
//import nl.hanze.project.model.Car;
import nl.hanze.project.model.*;
import nl.hanze.project.model.Interfaces.TypeOfCar;
import nl.hanze.project.view.HistogramView;
import nl.hanze.project.view.SimulatorView;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;


public class Simulator extends AbstractController implements TypeOfCar {


    public static List<Reservation> reservations;

    private double carsInGarage;
    private static List<Double> population = new ArrayList<>();
    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue; // wordt gebruikt voor ParkingPassCar & ReservedCar
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;
    //public ArrayList<Reservation> reservations  = new ArrayList<>();

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour
    int weekDayReservedArrivals; // average number of arriving cars per hour
    int weekendReservedArrivals = 0; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    public Simulator() {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        simulatorView = new SimulatorView(3, 6, 30);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    public int getWeekDayArrivals() {
        return weekDayArrivals;
    }

    private void tick() {
    	advanceTime();
    	makeReservation();
    	reservations = Reservation.getReservations();
    	for (Reservation res : reservations){
    	    if(res.tickRes(minute)) {
    	        weekDayReservedArrivals += 1;
            }
        }
        //reservations = Reservation.getReservedCarArrayList();
    	handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;

        }
        while (hour > 23) {
            hour -= 24;
            day++;
            List<Double> data = Simulator.getPopulation();
            carsInGarage = 0;
        }
        while (day > 6) {
            day -= 7;
        }

    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void updateViews(){
    	simulatorView.tick();
        // Update the car park view.
        simulatorView.updateView();	
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
        numberOfCars=getNumberOfCars(weekDayReservedArrivals, weekendReservedArrivals); // Behandeld ReservedCars
        addArrivingCars(numberOfCars, RESERVED);
     /*   numberOfCars=getNumberOfCars(weekDayReservedArrivals, weekendReservedArrivals); // Behandeld ReservedCars
        addArrivingCars(numberOfCars, RESERVATION); */
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			simulatorView.getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = simulatorView.getFirstFreeLocation();
            simulatorView.setCarAt(freeLocation, car);
            i++;
            carsInGarage++;
        }
    }

/*
    private void pickLocationForReservation(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 &&
                simulatorView.getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = Reservation.getLocation();
            simulatorView.setReservationAt(Reservation.getLocation(), car);
            i++;
        }
    }
*/

    /**
     * Maakt een reservering aan
     *
     * @author w.j.schuringa & l.g.caspers
     */
    private void makeReservation() {
        Location freeLocation = simulatorView.getFirstFreeLocation();
        Random random = new Random();
        int reservationMinute = random.nextInt(60);
        int reservationHour = random.nextInt(24);
        int reservationDay = random.nextInt(7);
        Reservation reservation = new Reservation(freeLocation,reservationDay,reservationHour,reservationMinute);
        //reservations.add(reservation);
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = simulatorView.getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = simulatorView.getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
            carsInGarage--;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, int type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
        case RESERVED: // case werkt hetzelfde als hierboven, enige verandere is ReservedCar() toegevoegd
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ReservedCar());
                    weekDayReservedArrivals -= 1;
                }
                break;
       /* case RESERVATION: // case werkt hetzelfde als hierboven, enige verandere is ReservedCar() toegevoegd
                for (int i = 0; i < numberOfCars; i++) {
                    for(Reservation res : reservations){
                        entrancePassQueue.addCar(res);
                        weekDayReservedArrivals -= 1;
                    }
                }
                break; */
        }

    }

    private void carLeavesSpot(Car car){
    	simulatorView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    /**
     * returnt de dag
     * @return int day
     * @author w.j.schuringa & l.g.caspers
     */
    public int getDay(){
        return day;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute() {
        return minute;
    }


    /**
     * return  amount of cars
     * @return int amountOfCarsInGarage
     * @author Maurice Wijker
     */

    public double getCarsInGarage(){ return this.carsInGarage ;};

    public static List<Double> getPopulation() {
        return population;
    }
}
