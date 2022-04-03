package ecosystem;

import java.util.Random;

public abstract class Animal {
	private int lastLocation;
	private int currentLocation;
	private int nextLocation;
	private int riverSize;
	private Random isGoingToMove;
	private Random directionRandomizer;
	
	public Animal(int currentLocation, int riverSize) {
		this.lastLocation = -1;
		this.currentLocation = currentLocation;
		this.riverSize = riverSize;
		isGoingToMove = new Random();
		directionRandomizer = new Random();
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
				nextLocation++;
			else
				nextLocation--;
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
	}
	
	public void commitChangeToLocation() {
		this.lastLocation = currentLocation;
		this.currentLocation = nextLocation;
	}
	
	public void revertChangeToLocation() {
		this.currentLocation = lastLocation;
	}
	
	public void interactWith(Animal animal) {
		if (animal instanceof Predator)
			interactWith((Predator) animal);
		else if (animal instanceof Prey)
			interactWith((Prey) animal);
		else
			System.out.println("Não foi possível identificar o tipo do animal passado para a função");
	}
	
	public abstract void interactWith(Predator predator);
	
	public abstract void interactWith(Prey prey);
	
}
