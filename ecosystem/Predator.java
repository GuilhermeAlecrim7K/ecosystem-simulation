package ecosystem;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public abstract class Predator extends Animal {

	public Predator(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}

	public Predator(int riverSize) {
		super(riverSize);
	}

	@Override
	public List<Entry<Integer, Animal>> interactWith(Predator predator) {
		List<Entry<Integer, Animal>> result;
		result = interactWithSameType(predator);
		return result;
	}
	
	@Override
	public List<Entry<Integer, Animal>> interactWith(Prey prey) {
		List<Entry<Integer, Animal>> result = new LinkedList<>();
		Entry<Integer,Animal> entry = new AbstractMap.SimpleEntry<>(this.nextLocation, this);
		result.add(entry);
		return result;
	}
}
