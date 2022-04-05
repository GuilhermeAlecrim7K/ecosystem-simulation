package ecosystem;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		} while (isNotValidLocation(nextLocation));
	}
	
	private boolean isNotValidLocation(int coordinate) {
		if (coordinate >= 0 && coordinate < riverSize)
			return false;
		else
			return true;
	}
	
	private void stay() {
		nextLocation = currentLocation;
		lastLocation = currentLocation;
	}
	
	public void commitChangeToLocation() {
		this.lastLocation = currentLocation;
		this.currentLocation = nextLocation;
	}
	
	public List<Map.Entry<Integer, Animal>> interactWith(Animal animal) {
		List<Map.Entry<Integer, Animal>> result;
		if (animal instanceof Predator)
			result = interactWith((Predator) animal);
		else if (animal instanceof Prey)
			result = interactWith((Prey) animal);
		else
			result = null;
		return result;
	}
	
	public abstract List<Entry<Integer, Animal>> interactWith(Predator predator);
	
	public abstract List<Entry<Integer, Animal>> interactWith(Prey prey);
	
	protected abstract Animal makeChild();
	
	protected List<Entry<Integer, Animal>> interactWithSameType(Animal animal) {
		stay();
		Animal newChild = makeChild();
		List<Entry<Integer, Animal>> result = new LinkedList<>();
		Entry<Integer, Animal> entry = new AbstractMap.SimpleEntry<Integer, Animal>(null, newChild);
		result.add(entry);
		return result;
	}

	public void defineInitialPosition(int initialPosition) {
		this.lastLocation = initialPosition;
		this.currentLocation = initialPosition;
		this.nextLocation = initialPosition;
	}
	
}
