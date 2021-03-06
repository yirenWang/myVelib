package core.station;

import utils.Point;

/**
 * Concrete station which behaves exactly as the abstract station would, 5
 * minutes of time credit are given on return. <br>
 * 
 * Extends Station
 * 
 * @see Station
 * 
 * @author matto
 *
 */
public class PlusStation extends Station {

	// Constructors
	
	/**
	 * Create a station with the given number of parking slots, coordinates and
	 * online status
	 * 
	 * @param numberOfParkingSlots
	 * @param coordinates
	 * @param online
	 */
	protected PlusStation(int numberOfParkingSlots, Point coordinates, Boolean online) {
		super(numberOfParkingSlots, coordinates, true, 5);
	}

	/**
	 * Create a station with the given number of parking slots, coordinates. By
	 * default, online status is true and bonus time credit on return for plus
	 * stations is 5.
	 * 
	 * @param numberOfParkingSlots
	 * @param coordinates
	 */
	protected PlusStation(int numberOfParkingSlots, Point coordinates) {
		super(numberOfParkingSlots, coordinates, true, 5);
	}
	
	@Override
	public String toString() {
		String s = "Plus Station [Id: " + super.getId() + "\n";
		s += "Parking Slots: " + super.getParkingSlots().toString() + "\n";
		s += "Coordinates: " + super.getCoordinates().toString() + "\n";
		s += "Online: " + super.getOnline() + "]";
		return s;

	}
}
