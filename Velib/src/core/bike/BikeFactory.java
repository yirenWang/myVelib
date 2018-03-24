package core.bike;

import core.BikeType;

/**
 * Bike factory which creates different types of bikes depending of the given type
 * @author matto
 *
 */
public class BikeFactory {
	
	/**
	 * Creates and returns a Bike corresponding to the given type
	 * @param bikeType - the type of bike to create
	 * @return a Bike
	 * @throws InvalidBikeTypeException if the given type is not recognized as a valid bike type
	 */
	public Bike createBike(BikeType bikeType) throws InvalidBikeTypeException {
		if (bikeType == null) {
			throw new InvalidBikeTypeException(bikeType);
		}
		
		switch(bikeType) {
			case MECH:
				return new MechBike();
			case ELEC:
				return new ElecBike();
			default:
				throw new InvalidBikeTypeException(bikeType);
		} 
	}
}
