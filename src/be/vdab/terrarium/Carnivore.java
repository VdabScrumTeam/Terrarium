package be.vdab.terrarium;

public class Carnivore extends Animal {
	
	public Carnivore (Coordinate coordinate) {
		super(coordinate);
	}
	
	@Override
	public char draw() {
		return 'C';
	}
	
	public void vecht(Carnivore carnivoreToTheRight) {
		this.setLifeforce(this.getLifeforce() + carnivoreToTheRight.getLifeforce());
	}
}