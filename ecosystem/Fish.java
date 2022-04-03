package ecosystem;

public class Fish extends Prey {

	public Fish(int currentLocation, int riverSize) {
		super(currentLocation, riverSize);
	}
	
	public Fish(int riverSize) {
		super(riverSize);
	}

	@Override
	protected Animal makeChild() {
		return new Fish(this.riverSize);
	}

}
