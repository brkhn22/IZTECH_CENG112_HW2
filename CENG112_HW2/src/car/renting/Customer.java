package car.renting;

public class Customer {
	
	private String id;
	private double threshHold;
	
	public Customer(String id, double threshHold) {
		this.id = id;
		this.threshHold = threshHold;
	}
	
	public double getThreshHold() {
		return this.threshHold;
	}
	public void decreaseThreshHold() {
		this.threshHold = ((double)Math.round(this.threshHold*90))/100;
	}
	public boolean isCarAppropriate(Car car) {
		return this.threshHold <= car.getThreshHold();
	}
	public String getId() {
		return id;
	}
}
