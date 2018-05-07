package be.vdab.terrarium;

public class Plant extends Organism {

	public Plant(Coordinate coordinate) {
		super(coordinate);
	}
	@Override
	public char draw() {
		return 'P';
	}
	
}
