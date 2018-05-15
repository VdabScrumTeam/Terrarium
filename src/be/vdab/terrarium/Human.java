package be.vdab.terrarium;

public class Human extends Organism {
	
	public Human(Coordinate coordinate) {
		super(coordinate);
		
	}
	
	@Override
	public char draw() {
		return 'M';
	}
	
	

}
