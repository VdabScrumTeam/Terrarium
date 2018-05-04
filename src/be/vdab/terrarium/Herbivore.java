package be.vdab.terrarium;

public class Herbivore extends Animal {

	public Herbivore () {
		this.lifeforce = 1;
	}
	
	@Override
	public char draw() {
		return 'H';
	}
	
	public Herbivore sex() {
		return new Herbivore();
	}
}