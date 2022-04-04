package ecosystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class River {
	private List<Animal> location;
	private int riverLength;
	private List<Integer> indexesOfNullLocations;
	
	public River(Integer riverLength) {
		this.riverLength = riverLength;
		location = new ArrayList<>(riverLength);
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
			if (animal instanceof Fish)
				return true;
		}
		return false;
	}

	private void instantiateAnimals() {
		final int Fish = 1;
		final int Bear = 2;
		
		Random random = new Random();
		for (int i = 0; i < riverLength; i++) {
			int animalCase = random.nextInt(3);
			switch (animalCase) {
			case Fish:
				location.add(new Fish(i, riverLength));
				break;
			case Bear:
				location.add(new Bear(i, riverLength));
				break;
			default:
				location.add(null);
				indexesOfNullLocations.add(i);
				break;
			}
		}
	}

	private void randomProcess() {
		for (int i = 0; i < riverLength; i++) {
			i = getLocationOfNextAnimalStartingWith(i);
			callAnimalAction(i);
		}
	}
	
	private int getLocationOfNextAnimalStartingWith(int index) {
		while(location.get(index) == null) {
			index++;
		}
		return index;
	}
	
	private void callAnimalAction (int indexInRiver) {
		Animal currentAnimal = location.get(indexInRiver);
		int nextLocation = currentAnimal.getNextLocation();
		
		if (nextLocationIsEmpty(nextLocation)) 
			updateLocation(currentAnimal);		
		else {
			makeAnimalsInteract(currentAnimal, location.get(nextLocation));
		}			
	}
	
	private void makeAnimalsInteract(Animal currentAnimal, Animal nextAnimal) {
		Map<Integer, Animal> interactionResults = currentAnimal.interactWith(nextAnimal);
		if (hasNewAnimal(interactionResults)){
			Animal animal = interactionResults.remove(null);
			animal.defineInitialPosition(getRandomNullLocation());
		}				
		for (Map.Entry<Integer, Animal> entry: interactionResults.entrySet()) {
			int index = entry.getKey();
			Animal animal = entry.getValue();
			location.set(animal.getCurrentLocation(), null);
			indexesOfNullLocations.add(animal.getCurrentLocation());
			location.set(index, animal);
			indexesOfNullLocations.remove(Integer.valueOf(index));
			animal.commitChangeToLocation();
		}
	}
	
	private boolean hasNewAnimal(Map<Integer, Animal> interactionResults) {
		return interactionResults.containsKey(null);
	}
	
	private boolean nextLocationIsEmpty(Integer index) {
		return location.get(index) == null;
	}
	
	private void updateLocation(Animal animal) {
		int currentLocation = animal.getCurrentLocation();
		// CANNOT CALL METHOD -> int nextLocation = animal.getNextLocation();
		location.set(currentLocation, null);
		indexesOfNullLocations.add(animal.getCurrentLocation());
		location.set(nextLocation, animal);
		indexesOfNullLocations.remove(Integer.valueOf(nextLocation));
		animal.commitChangeToLocation();
	}

	private Integer getRandomNullLocation() {
		Random random = new Random();
		int randomIndex = indexesOfNullLocations.remove(random.nextInt(indexesOfNullLocations.size()));
		return randomIndex;
	}

}
