package be.vdab.terrarium;

public class Herbivore extends Animal {

	public Herbivore (Coordinate coordinate) {
		super(coordinate);
	}
	
	@Override
	public char draw() {
		return 'H';
	}
}