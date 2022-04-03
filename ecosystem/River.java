package ecosystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class River {
	private List<Animal> location;
	private List<Integer> indexesOfNullLocations;
	
	public River(Integer riverLength) {
		location = new ArrayList<>(riverLength + 1);
		indexesOfNullLocations = new LinkedList<>();
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
				indexesOfNullLocations.add(i);
				break;
			}
		}
	}

	private void randomProcess() {
		for (int i = 0; i < location.size(); i++) {
			i = getLocationOfNextAnimalStartingWith(i);
			Animal currentAnimal = location.get(i);
			int nextLocation = currentAnimal.getNextLocation();
			if (nextLocationIsEmpty(nextLocation)) {
				updateLocation(currentAnimal);
				currentAnimal.commitChangeToLocation();
			}
			else {
				Map<Integer, Animal> indexAnimalPairs;
				indexAnimalPairs = currentAnimal.interactWith(location.get(nextLocation));
				if (indexAnimalPairs.containsKey(null)){
					Animal animal = indexAnimalPairs.remove(null);
					animal.defineInitialPosition(getRandomNullLocation());
				}
				for (Map.Entry<Integer, Animal> entry: indexAnimalPairs.entrySet()) {
					int index = entry.getKey();
					Animal animal = entry.getValue();
					animal.commitChangeToLocation();
					location.set(animal.getCurrentLocation(), null);
					indexesOfNullLocations.add(animal.getCurrentLocation());
					location.set(index, animal);
					indexesOfNullLocations.remove(Integer.valueOf(index));
					animal.commitChangeToLocation();
				}
			}			
		}
	}
	
	private void updateLocation(Animal animal) {
		location.set(animal.getCurrentLocation(), null);
		animal.commitChangeToLocation();
		location.set(animal.getCurrentLocation(), animal);
	}

	private int getLocationOfNextAnimalStartingWith(int index) {
		while(location.get(index) == null) {
			index++;
		}
		return index;
	}

	private boolean nextLocationIsEmpty(Integer index) {
		return location.get(index) == null;
	}

	private Integer getRandomNullLocation() {
		Random random = new Random();
		int randomIndex = indexesOfNullLocations.remove(random.nextInt(indexesOfNullLocations.size()));
		return randomIndex;
	}

}
