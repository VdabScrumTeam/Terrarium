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
		if(coordinate.getRow() != -1) {
			this.coordinate = coordinate;
			this.lifeforce = 0;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public abstract char draw();
	
}