package core.test.scenarios;

import core.Network;
import core.bike.InvalidBikeTypeException;
import core.card.CardVisitorFactory;
import core.card.InvalidCardTypeException;
import core.ridePlan.InvalidRidePlanPolicyException;
import core.ridePlan.NoValidStationFoundException;
import core.ridePlan.RidePlan;
import core.user.User;
import utils.DateParser;
import utils.Point;

public class Scenario2 {
	public static void main(String[] args) {
		// We are in the year 2000, January the 1st
		
		// create the network 
		Network n = new Network("myVelib", 10, 10, 10, 0.75, 0.3, 0.5, DateParser.parse("01/01/2000T09:00:00"));
		CardVisitorFactory cardVisitorFactory = new CardVisitorFactory();
		// create users
		User alice = null, bob = null, charles = null;
		try {
			alice = new User("Alice", new Point(0,0), cardVisitorFactory.createCard("NO_CARD"));
			bob = new User("Bob", new Point(0,0), cardVisitorFactory.createCard("VLIBRE_CARD"));
			charles = new User("Charles", new Point(0,0), cardVisitorFactory.createCard("VMAX_CARD"));
		} catch (InvalidCardTypeException e) {
			System.out.println("Invalid card type given.");
		}
		
		// add users to network 
		n.addUser(alice);
		n.addUser(bob);
		n.addUser(charles);
		
		// create landmarks
		Point home = new Point(0, 0);
		Point school = new Point(5,5);
		Point supermarket = new Point(7,2);
		Point library = new Point(2,6);
		
		// plan a ride for Bob 
		System.out.println("\nBob plans a ride using the prefer plus policy, for a mech bike:");
		RidePlan bobRidePlanGo = null;
		try {
			bobRidePlanGo = n.createRidePlan(home, supermarket, bob, "PREFER_PLUS", "MECH");
		} catch (InvalidBikeTypeException e) {
			System.out.println("Invalid bike type given");
			return;
		} catch (InvalidRidePlanPolicyException e) {
			System.out.println("Invalid policy given");
			return;
		} catch (NoValidStationFoundException e) {
			System.out.println("No valid station was found");
			return;
		}		
		System.out.println(bobRidePlanGo);
		
		// Time bob might take to go 
		// System.out.println(bobRidePlanGo.approximateTime());
		
		// bob rents the bike at 9:30am 
		System.out.println("\nBob rents an mech bike at 9:00am at the source station of his plan:");
		String bobRentBikeMessage = n.rentBike(bob.getId(), bobRidePlanGo.getSourceStation().getId(), "MECH", DateParser.parse("01/02/2000T09:30:00"));
		System.out.println(bobRentBikeMessage);
		
		// Dest Station 1 goes offline 
		bobRidePlanGo.getDestinationStation().setOnline(false);
		// bob create another rideplan 
		
		RidePlan bobRidePlanGo2 = null;
		try {
			bobRidePlanGo2 = n.createRidePlan(home, supermarket, bob, "PREFER_PLUS", "MECH");
		} catch (InvalidBikeTypeException e) {
			System.out.println("Invalid bike type given");
			return;
		} catch (InvalidRidePlanPolicyException e) {
			System.out.println("Invalid policy given");
			return;
		} catch (NoValidStationFoundException e) {
			System.out.println("No valid station was found");
			return;
		}
		System.out.println(bobRidePlanGo2);

		// bob returns the bike at 10.45am		
		System.out.println("\nBob returns the mech bike at 10:45am at the destination station of his plan:");
		String bobReturnBikeMessage = n.returnBike(bob.getId(), bobRidePlanGo2.getDestinationStation().getId(), DateParser.parse("01/02/2000T10:45:00"));
		System.out.println(bobReturnBikeMessage);
		
		// Time bob might take to return 
		// System.out.println(bobRidePlanReturn.approximateTime());
		
		System.out.println("\nBob plans a new ride using the avoid plus policy, for an elec bike:");
		RidePlan bobRidePlanReturn = null;
		try {
			bobRidePlanReturn = n.createRidePlan(home, supermarket, bob, "AVOID_PLUS", "MECH");
		} catch (InvalidBikeTypeException e) {
			System.out.println("Invalid bike type given");
			return;
		} catch (InvalidRidePlanPolicyException e) {
			System.out.println("Invalid policy given");
			return;
		} catch (NoValidStationFoundException e) {
			System.out.println("No valid station was found");
			return;
		}
		
		System.out.println(bobRidePlanReturn);

		// System.out.println(bobRidePlanReturn.toString());
		// bob rents the bike at 13:00

		System.out.println("\nBob rents an elec bike at 1:00pm at the source station of his plan:");
		String bobRentBikeMessage2 = n.rentBike(bob.getId(), bobRidePlanReturn.getSourceStation().getId(), "ELEC", DateParser.parse("01/02/2000T13:00:00"));
		System.out.println(bobRentBikeMessage2);
		// bob returns the bike at 10.45am
		System.out.println("\nBob returns the elec bike at 2:15pm at the destination station of his plan:");
		String bobReturnBikeMessage2 = n.returnBike(bob.getId(), bobRidePlanReturn.getDestinationStation().getId(), DateParser.parse("01/02/2000T14:15:00"));
		System.out.println(bobReturnBikeMessage2);
	}
}
