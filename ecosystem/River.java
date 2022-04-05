package ecosystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

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
		for (Animal animal : location) {
			if (animal != null)
				callAnimalAction(animal);
		}
	}
	
	private void callAnimalAction (Animal currentAnimal) {
		int nextLocation = currentAnimal.getNextLocation();
		
		if (nextLocationIsSameOrEmpty(currentAnimal, nextLocation)) 
			updateLocation(currentAnimal, nextLocation);		
		else
			makeAnimalsInteract(currentAnimal, location.get(nextLocation));
	}
	
	private boolean nextLocationIsSameOrEmpty(Animal animal, Integer nextLocation) {
		return animal.getCurrentLocation() == nextLocation || location.get(nextLocation) == null;
	}
	
	private void updateLocation(Animal animal, int nextLocation) {
		int currentLocation = animal.getCurrentLocation();
		location.set(currentLocation, null);
		indexesOfNullLocations.add(animal.getCurrentLocation());
		location.set(nextLocation, animal);
		indexesOfNullLocations.remove(Integer.valueOf(nextLocation));
		animal.commitChangeToLocation();
	}
	
	private void makeAnimalsInteract(Animal currentAnimal, Animal nextAnimal) {
		List<Entry<Integer, Animal>> interactionResults = currentAnimal.interactWith(nextAnimal);
		if (hasNewAnimal(interactionResults))
			putInRiver(interactionResults.remove(0).getValue());				
		for (Map.Entry<Integer, Animal> entry: interactionResults) {
			Animal animal = entry.getValue();
			int nextLocation = entry.getKey();
			updateLocation(animal, nextLocation);
		}
	}
	
	private void putInRiver(Animal newAnimal) {
		Integer position = getRandomNullLocation();
		if (position != null) {
			newAnimal.defineInitialPosition(position);
			updateLocation(newAnimal, newAnimal.getCurrentLocation());
		}
	}
	
	private boolean hasNewAnimal(List<Entry<Integer, Animal>> interactionResults) {
		for (Entry<Integer, Animal> animal: interactionResults) {
			if (animal.getKey() == null)
				return true;
		}
		return false;
	}

	private Integer getRandomNullLocation() {
		Random random = new Random();
		Integer randomIndex;
		int listSize = indexesOfNullLocations.size();
		if (listSize > 0)
			randomIndex = indexesOfNullLocations.remove(random.nextInt(listSize));
		else
			randomIndex = null;
		return randomIndex;
	}
	
	public List<Animal> getLocation(){
		return this.location;
	}

}
