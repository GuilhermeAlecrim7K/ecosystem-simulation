package ecosystem;

public abstract class Predator extends Animal {

	public Predator(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}

	@Override
	public void interactWith(Predator predator) {
		

	}
	
	@Override
	public void interactWith(Prey prey) {
		

	}
}
