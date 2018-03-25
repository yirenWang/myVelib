package core.ridePlan;

import java.util.HashMap;

import core.Network;

/**
 * Exception thrown when a valid station for a given plan ride strategy and for a given network is not found.
 * @author matto
 *
 */
public class NoValidStationFoundException extends Exception {
	private final Network network;
	private final RidePlanPolicyName policyName;

	public NoValidStationFoundException(Network network, RidePlanPolicyName policyName) {
		this.network = network;
		this.policyName = policyName;
	}

	public Network getNetwork() {
		return network;
	}

	public RidePlanPolicyName getPolicyName() {
		return policyName;
	}
	
	@Override
	public String getMessage() {
		return "No valid source or destination station could be found for the " + policyName + " policy for network " + network.getName() + ".";
	}
}
