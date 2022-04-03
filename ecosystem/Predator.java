package ecosystem;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Predator extends Animal {

	public Predator(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}

	@Override
	public Map<Integer, Animal> interactWith(Predator predator) {
		Map<Integer, Animal> result = new LinkedHashMap<>();
		//TODO: take the location of both objects and 
		result.put(this.lastLocation, this);
		result.put(null, predator);
		Animal newCub = makeChild();
		result.put(null, newCub);
		
		return result;
	}
	
	@Override
	public Map<Integer, Animal> interactWith(Prey prey) {
		Map<Integer, Animal> result = new LinkedHashMap<>();
		
		
		return result;
	}
}
