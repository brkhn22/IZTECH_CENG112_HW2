package car.renting;

import java.util.Scanner;

import implementations.*;
import interfaces.*;

public class CarRentingApp {


	private static DequeInterface<Car> carDeque;
	private static QueueInterface<Customer> customerQueue;
	private static ListInterface<Customer> waitingList;
	private static ListInterface<Car> carList;
	
	public static void main(String[] args) {
		// Get inputs
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter available car count, n =");
		int n = scan.nextInt();
		System.out.print("Enter customer count, k =");
		int k = scan.nextInt();
		scan.close();
		// create lists and queues
		waitingList = new ArrayList<Customer>(k);
		carList = new ArrayList<Car>(n);
		
		carDeque = new ArrayDeque<Car>(carList.getLength());
		customerQueue = new ArrayQueue<Customer>(waitingList.getLength());
		// create cars
		for(int i = 0; i < n; i++) {
			String id = "Car"+i;
			double threshHold = getRandomThreshHold();
			Car car = new Car(id, threshHold);
			carList.add(car);
		}
		// create customers
		for(int i = 0; i < k; i++) {
			String id = "Customer"+i;
			double threshHold = getRandomThreshHold();
			Customer ct = new Customer(id, threshHold);
			waitingList.add(ct);
		}
		System.out.println();
		//Init days
		int day = 1;
		while(!waitingList.isEmpty() || !customerQueue.isEmpty()) {
			System.out.println("**********Day"+day+"**********");
			// get cars into deque
			for(int i = 1; i <= carList.getLength(); i++) {
				Car car = carList.getEntry(i);
				if(car.getOccupancy() == 0)
					carDeque.addToBack(car);
				else if(car.getOccupancy() == 1) {
					carDeque.addToFront(car);
				}
				car.decreaseOccupancy();
			}
			while(!carDeque.isEmpty() && (!waitingList.isEmpty() || !customerQueue.isEmpty())) {
				// set customer queue
				while(!waitingList.isEmpty()) {
					Customer c = waitingList.remove(1);
					customerQueue.enqueue(c);
				}
				Car currentCar = carDeque.removeFront();
				System.out.println("Current "+currentCar.getId()+" quality="+currentCar.getThreshHold()+" is offering to");
				boolean carOccupied = false;
				// check if there is an appropriate customer for the currentCar.
				while(!customerQueue.isEmpty() && !carOccupied) {
					Customer currentCustomer = customerQueue.dequeue();
					System.out.print("\tCurrent "+currentCustomer.getId()+" quality="+currentCustomer.getThreshHold());
					if(currentCustomer.isCarAppropriate(currentCar)) {
						currentCar.rentCar(getRandomOccupancy(), currentCustomer);
						carOccupied = true;
						System.out.print("\t --accepted\n");
					}else {
						waitingList.add(currentCustomer);
						System.out.print("\t --not accepted\n");
					}
				}
				if(!carOccupied)
					System.out.println("\t ---not accepted by any customers---");
			}
			System.out.println("All cars have seen");
			if(!waitingList.isEmpty()) {
				System.out.println("But there are still customers waiting.");
				listRentedCars();
				listAvailableCars();
				// decrease remaining customer thresh hold.
				decreaseCustomerThreshHold();
				System.out.println("**********End of the Day**********:\n");
			}
			day++;
		}
		System.out.println("All Customer rent a car."); 
	} 
	private static double getRandomThreshHold() {	
		return ((double)Math.round((Math.random()*200)+100))/100;
	}
	private static int getRandomOccupancy() {
		return (int)Math.round((Math.random()*4)+1);
	}
	private static void listRentedCars() {
		ListInterface<Car> rentedCars = new ArrayList<Car>();
		for(int i = 1; i <= carList.getLength(); i++) {
			Car car = carList.getEntry(i);
			if(car.getOccupancy()>0) {
				rentedCars.add(car);
			}	
		}
		if(rentedCars.getLength()>0) {
			System.out.println("Rented Cars: ");
			for(int i = 1; i <= rentedCars.getLength(); i++) {
				Car car = rentedCars.getEntry(i);
				String custId = car.getCustomer().getId();
				String carId = car.getId();
				int occupancy = car.getOccupancy();
				System.out.println("\t"+carId+" by "+custId+" occupancy="+occupancy);
			}
		}
	}
	private static void listAvailableCars() {
		ListInterface<Car> availableCars = new ArrayList<Car>();
		for(int i = 1; i <= carList.getLength(); i++) {
			Car car = carList.getEntry(i);
			if(car.getOccupancy()<=0) 
				availableCars.add(car);
		}
		if(availableCars.getLength() > 0) {
			System.out.println("Available Cars: ");
			for(int i = 1; i <= availableCars.getLength(); i++) {
				Car car = availableCars.getEntry(i);
				String carId = car.getId();
				System.out.println("\t"+carId);
			}
		}
	}
	private static void decreaseCustomerThreshHold() {
		for(int i = 1; i <= waitingList.getLength();i++) 
			waitingList.getEntry(i).decreaseThreshHold();
	} 
	
}
