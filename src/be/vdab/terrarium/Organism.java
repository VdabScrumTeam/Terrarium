package be.vdab.terrarium;

public abstract class Organism {
	
	private Coordinate coordinate;
	private int lifeforce = 1;
	
	public Organism(Coordinate coordinate) {
		this.setCoordinate(coordinate);
	}
	
	public int getLifeforce() {
		return lifeforce;
	}

	public void setLifeforce(int lifeforce) {
		this.lifeforce = lifeforce;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		if(coordinate != null) {
			this.coordinate = coordinate;
		}
	}

	public abstract char draw();
	
}