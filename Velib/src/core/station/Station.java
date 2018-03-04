package core.station;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import core.User;
import core.bike.Bike;
import core.point.Point;

public abstract class Station extends Observable  {
	private int id;
	private ArrayList<ParkingSlot> parkingSlots; 
	private Point coordinates;
	private Boolean online;
	private Set<User> observers = new HashSet<User>(); 
	// Statistics
	private int totalRentals = 0;
	private int totalReturns = 0;

	
	public Station(int numberOfParkingSlots, Point coordinates, Boolean online) {
		super();
		this.id = StationIDGenerator.getInstance().getNextIDNumber();
		for (int i=0; i < numberOfParkingSlots; i++) {
			this.parkingSlots.add(new ParkingSlot());
		}
		this.coordinates = coordinates;
		this.online = online;
	}
	

	public Station(int numberOfParkingSlots, Point coordinates) {
		super();
		this.id = StationIDGenerator.getInstance().getNextIDNumber();
		for (int i=0; i < numberOfParkingSlots; i++) {
			this.parkingSlots.add(new ParkingSlot());
		}
		this.coordinates = coordinates;
		this.online = true;
	}
	

	public abstract void rentBike(Bike bike); // you don't need the type ? just need instanceof 
	public abstract void returnBike(Bike bike);
	
	public void addObserver(User user) {
		if(this.observers.add(user)) {
			System.out.println("User " + user.getName() + "is observing station S" + this.id);		
		} else {
			System.out.println("User is already observing this station S" + this.id);
		}
	}
	
	public void deleteObserver(User user) {
		if(this.observers.remove(user)) {
			System.out.println("User " + user.getName() + "stopped observing this station" + this.id);
		} else {
			System.out.println("User is not observing this station S" + this.id);
		}
	}
	
	public void notifyObservers() {
		for (User user : observers) {
			user.update(this, null);
		}
	}
	
	public void createParkingSlot() {
		
	}
	
	/**
	 * Calculate occupation rate
	 * Out of order parking slots are ignored
	 */
	public void computeOccupationRate() {
		
	}
	
	/**
	 * Display Statistics of station
	 */
	public void displayBalance() {
		
	}
}
