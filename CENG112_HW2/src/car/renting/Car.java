package car.renting;

public class Car {

	private String id;
	private double threshHold;
	private int occupancy;
	private Customer customer;
	
	public Car(String id, double threshHold) {
		this.id = id;
		this.threshHold = threshHold;
		this.occupancy = 0;
	}
	
	public double getThreshHold() {
		return this.threshHold;
	}
	public int getOccupancy() {
		return this.occupancy;
	}
	public Customer getCustomer() {
		return this.customer;
	}
	public void rentCar(int occupancy, Customer customer) {
		this.occupancy = occupancy;
		this.customer = customer;
	}
	public void decreaseOccupancy() {
		if(occupancy > 0) {
			occupancy--;
			if(occupancy<=0 && this.customer != null)
				this.customer = null;
		}
	}
	public String getId() {
		return id;
	}
}
