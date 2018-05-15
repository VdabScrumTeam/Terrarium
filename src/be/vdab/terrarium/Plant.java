package be.vdab.terrarium;

public class Plant extends Organism {

	public Plant(Coordinate coordinate) {
		super(coordinate);
		this.setLifeforce(1);
	}
		
	@Override
	public char draw() {
		return 'P';
	}
	
}
