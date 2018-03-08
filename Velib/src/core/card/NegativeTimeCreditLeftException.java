package core.card;

public class NegativeTimeCreditLeftException extends Exception {
	private final int timeCredit;
	
	public NegativeTimeCreditLeftException(int timeCredit) {
		this.timeCredit = timeCredit;
	}

	public int getTimeCredit() {
		return timeCredit;
	}
}
