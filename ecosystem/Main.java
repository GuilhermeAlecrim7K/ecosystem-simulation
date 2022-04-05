package ecosystem;

public class Main {

	public static void main(String[] args) {
		River river = new River(10);
		river.begin();
		System.out.println(river.getLocation());
	}

}
