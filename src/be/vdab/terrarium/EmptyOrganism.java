package be.vdab.terrarium;

public class EmptyOrganism extends Organism{

	public EmptyOrganism(Coordinate coordinate) {
		super(coordinate);
		this.setLifeforce(0);
		
	}
	@Override
	public char draw() {
		return '.';
	}
}
