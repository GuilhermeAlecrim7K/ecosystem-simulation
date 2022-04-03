package ecosystem;

import java.util.LinkedHashMap;

public abstract class Prey extends Animal {

	public Prey(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}

	@Override
	public LinkedHashMap<Integer, Animal> interactWith(Predator predator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedHashMap<Integer, Animal> interactWith(Prey prey) {
		// TODO Auto-generated method stub

	}

}
