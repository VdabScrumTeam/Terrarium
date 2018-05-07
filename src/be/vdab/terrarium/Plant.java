package be.vdab.terrarium;

public class Plant extends Organism {

	public Plant(Coordinate coordinate) {
		super(coordinate);
		this.lifeforce = 1;
	}
	@Override
	public char draw() {
		return 'P';
	}
	
}
