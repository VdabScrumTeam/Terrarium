package be.vdab.terrarium;

public class Animal extends Organism {
	int lifeforce = 0;
	
	public Animal(Coordinate coordinate) {
		super(coordinate);
	}
	
	public int getLifeforce() {
		return lifeforce;
	}

	public void setLifeforce(int lifeforce) {
		this.lifeforce = lifeforce;
	}

	@Override
	public char draw() {
		return 0;
	}
}