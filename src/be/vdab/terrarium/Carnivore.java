package be.vdab.terrarium;

public class Carnivore extends Animal {
	
	public Carnivore () {
		this.lifeforce = 0;
	}
	
	@Override
	public char draw() {
		return 'C';
	}
	
	public void vecht(Carnivore carnivoreToTheRight) {
		this.lifeforce = this.lifeforce + carnivoreToTheRight.lifeforce;
	}
}