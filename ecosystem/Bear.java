package ecosystem;

public class Bear extends Predator {

	public Bear(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);	
	}

	public Bear(int riverSize) {
		super(riverSize);
	}

	@Override
	protected Animal makeChild() {
		return new Bear(this.riverSize);
	}
	
}
