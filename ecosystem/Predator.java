package ecosystem;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Predator extends Animal {

	public Predator(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}

	public Predator(int riverSize) {
		super(riverSize);
	}

	@Override
	public Map<Integer, Animal> interactWith(Predator predator) {
		Map<Integer, Animal> result;
		result = interactWithSameType(predator);
		return result;
	}
	
	@Override
	public Map<Integer, Animal> interactWith(Prey prey) {
		Map<Integer, Animal> result = new LinkedHashMap<>();
		result.put(this.nextLocation, this);
		return result;
	}
}
