package ecosystem;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public abstract class Prey extends Animal {

	public Prey(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}

	public Prey(int riverSize) {
		super(riverSize);
	}

	@Override
	public List<Entry<Integer, Animal>> interactWith(Predator predator) {
		List<Entry<Integer, Animal>> result = new LinkedList<>();
		Entry<Integer, Animal> preyEntry = new AbstractMap.SimpleEntry<>(this.nextLocation, this);
		Entry<Integer, Animal> predatorEntry = new AbstractMap.SimpleEntry<>(this.nextLocation, predator);
		result.add(preyEntry);
		result.add(predatorEntry);
		return result;
	}

	@Override
	public List<Entry<Integer, Animal>> interactWith(Prey prey) {
		List<Entry<Integer, Animal>> result;
		result = interactWithSameType(prey);
		return result;
	}

}
