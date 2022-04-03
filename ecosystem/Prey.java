package ecosystem;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Prey extends Animal {

	public Prey(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}

	public Prey(int riverSize) {
		super(riverSize);
	}

	@Override
	public Map<Integer, Animal> interactWith(Predator predator) {
		Map<Integer, Animal> result = new LinkedHashMap<>();
		result.put(this.currentLocation, predator);
		return result;
	}

	@Override
	public Map<Integer, Animal> interactWith(Prey prey) {
		Map<Integer, Animal> result;
		result = interactWithSameType(prey);
		return result;
	}

}
