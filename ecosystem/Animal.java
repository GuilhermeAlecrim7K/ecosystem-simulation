package ecosystem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public abstract class Animal {
	protected int lastLocation, currentLocation, nextLocation;
	protected int riverSize;
	private Random isGoingToMove;
	private Random directionRandomizer;
	
	public Animal(int currentLocation, int riverSize) {
		this(riverSize);
		defineInitialPosition(currentLocation);
	}
	
	public Animal(int riverSize) {
		this.riverSize = riverSize;
		isGoingToMove = new Random();
		directionRandomizer = new Random();		
	}
	
	public int getLastLocation() {
		return lastLocation;
	}

	public int getCurrentLocation() {
		return currentLocation;
	}

	public int getNextLocation() {
		if (isGoingToMove.nextBoolean())
			move();
		else
			stay();
		return nextLocation;
	}
	
	private void move() {
		do {
			if (directionRandomizer.nextBoolean())
				nextLocation = currentLocation +1;
			else
				nextLocation = currentLocation -1;
		} while (isValidLocation(nextLocation));
	}
	
	private boolean isValidLocation(int coordinate) {
		if (coordinate >= 0 && coordinate < riverSize)
			return true;
		else
			return false;
	}
	
	private void stay() {
		nextLocation = currentLocation;
		lastLocation = currentLocation;
	}
	
	public void commitChangeToLocation() {
		this.lastLocation = currentLocation;
		this.currentLocation = nextLocation;
	}
	
	public Map<Integer, Animal> interactWith(Animal animal) {
		Map<Integer, Animal> result;
		if (animal instanceof Predator)
			result = interactWith((Predator) animal);
		else if (animal instanceof Prey)
			result = interactWith((Prey) animal);
		else
			result = null;
		return result;
	}
	
	public abstract Map<Integer, Animal> interactWith(Predator predator);
	
	public abstract Map<Integer, Animal> interactWith(Prey prey);
	
	protected abstract Animal makeChild();
	
	protected Map<Integer, Animal> interactWithSameType(Animal animal) {
		stay();
		Map<Integer, Animal> result = new LinkedHashMap<>();
		result.put(this.currentLocation, this);
		Animal newChild = makeChild();
		result.put(null, newChild);
		
		return result;
	}

	public void defineInitialPosition(int initialPosition) {
		this.lastLocation = initialPosition;
		this.currentLocation = initialPosition;
		this.nextLocation = initialPosition;
	}
	
}
