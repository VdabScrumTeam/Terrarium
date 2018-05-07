package be.vdab.terrarium;

public class EmptyOrganism extends Organism{

	
	public EmptyOrganism(Coordinate coordinate) {
		super(coordinate);
		
	}
	@Override
	public char draw() {
		return '.';
	}
}
