package be.vdab.terrarium;

public class Animal extends Organism {
	int lifeforce = 0;
	
	public Animal(Coordinate coordinate) {
		super(coordinate);
	}
	
	@Override
	public char draw() {
		return 0;
	}
}