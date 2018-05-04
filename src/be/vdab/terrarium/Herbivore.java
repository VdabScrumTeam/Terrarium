package be.vdab.terrarium;

public class Herbivore implements Organism {

	public Herbivore () {
		
	}
	
	
	@Override
	public char draw() {
		return 'H';
	}
	public Herbivore sex() {
		return new Herbivore();
	}
}
