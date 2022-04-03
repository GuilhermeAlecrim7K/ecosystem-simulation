package ecosystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class River {
	private List<Animal> location;
	private List<Integer> indexOfNullLocations;
	
	public River(Integer riverLength) {
		location = new ArrayList<>(riverLength);
		indexOfNullLocations = new LinkedList<>();
		instantiateAnimals();
	}
	
	public void begin() {
		while (riverHasFish()) {
			randomProcess();
		}
	}
	
	private boolean riverHasFish() {
		for (Animal animal : location) {
			if (animal instanceof Fish) {
				return true;
			}
		}
		return false;
	}

	private void instantiateAnimals() {
		final int Fish = 1;
		final int Bear = 2;
		
		Random random = new Random();
		for (int i = 0; i < location.size(); i++) {
			int animalCase = random.nextInt(3);
			switch (animalCase) {
			case Fish:
				location.add(new Fish(i, location.size()));
				break;
			case Bear:
				location.add(new Bear(i, location.size()));
				break;
			default:
				location.add(null);
				indexOfNullLocations.add(i);
				break;
			}
		}
	}

	private void randomProcess() {
		for (int i = 0; i < location.size(); i++) {
			//callAnimalAction() {
			if (location.get(i) != null) {
				Animal currentAnimal = location.get(i);
				int nextLocation = currentAnimal.getNextLocation();
				if (nextLocationIsEmpty(nextLocation)) 
					currentAnimal.commitChangeToLocation();
				else 
					currentAnimal.interactWith(location.get(nextLocation));
			}
			
		}
	}
	
	private boolean nextLocationIsEmpty(Integer index) {
		return location.get(index) == null;
	}

	private Integer getRandomNullLocation() {
		Random random = new Random();
		int randomIndex = indexOfNullLocations.get(random.nextInt(indexOfNullLocations.size()));
		return randomIndex;
	}
}
