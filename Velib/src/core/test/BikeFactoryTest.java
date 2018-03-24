package core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.BikeType;
import core.bike.Bike;
import core.bike.BikeFactory;
import core.bike.ElecBike;
import core.bike.InvalidBikeTypeException;
import core.bike.MechBike;

// FIXME: Comments
public class BikeFactoryTest {

	@Test
	public void testCreateBike() {
		BikeFactory bikeFactory = new BikeFactory();
		
		try {
			Bike mb = bikeFactory.createBike(BikeType.MECH);
			assertTrue(mb instanceof MechBike);
		} catch (InvalidBikeTypeException e) {
			fail("InvalidBikeTypeException thrown");
		}
		
		try {
			Bike eb = bikeFactory.createBike(BikeType.ELEC);
			assertTrue(eb instanceof ElecBike);
		} catch (InvalidBikeTypeException e) {
			fail("InvalidBikeTypeException thrown");
		}
	}
	
	@Test
	public void whenWrongBikeTypeGivenThenThrowException() {
		BikeFactory bikeFactory = new BikeFactory();
		
		try {
			Bike mb = bikeFactory.createBike(null);
			fail("InvalidBikeTypeException should have been thrown");
		} catch (InvalidBikeTypeException e) {
			assertTrue(true);
		}
	}

}
