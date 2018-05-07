package be.vdab.terrarium;

public abstract class Organism {
	
	public Coordinate coordinate;
	public int lifeforce;
	
	public int getLifeforce() {
		return lifeforce;
	}

	public void setLifeforce(int lifeforce) {
		this.lifeforce = lifeforce;
	}

	public Organism(Coordinate coordinate) {
		this.coordinate = coordinate;
		this.lifeforce = 0;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public abstract char draw();
	
}