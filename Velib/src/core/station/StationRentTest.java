package core.station;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import core.BikeType;
import core.Network;
import core.User;
import core.bike.ElecBike;
import core.bike.MechBike;
import core.card.NoCardVisitor;
import core.point.Point;

public class StationRentTest {
	
	// create a network
	static Network n = new Network();
	// Create User
	static User bob = new User("bob", new Point(0,0), new NoCardVisitor());
	
	// Stations
	static Station s = new PlusStation(10, new Point(0,0.1));
	static Station emptyS = new PlusStation(10, new Point(0,0.2));
	
	@BeforeClass
	public static void fillStationAndNetwork() {
		// add bikes to stations 
		s.addBike(new MechBike());
		s.addBike(new ElecBike());
		
		// add user and station to network
		n.addUser(bob);
		n.addStation(s);
		n.addStation(emptyS);
	}
	
	@Test (expected = Exception.class)
	public void errorWhenRentBikefromEmptyStation() throws Exception {
		n.rent(bob.getId(), emptyS.getId(), "MECH");
	}
	
	@Test
	public void stationHasOneLessBikeOfTheDesiredKindAfterRent() throws Exception {
		int mechBikes = s.getNumberOfBikes(BikeType.MECH);
		int elecBikes = s.getNumberOfBikes(BikeType.ELEC);
		n.rent(bob.getId(), s.getId(), "MECH");
		int remainingMechBikes = s.getNumberOfBikes(BikeType.MECH);
		int remainingElecBikes = s.getNumberOfBikes(BikeType.ELEC);
		assertEquals(mechBikes, remainingMechBikes + 1);
		assertEquals(elecBikes, remainingElecBikes);
	}

}