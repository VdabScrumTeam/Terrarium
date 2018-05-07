package be.vdab.terrarium;

public abstract class Organism {
	
	public Coordinate coordinate;
	
	public Organism(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public abstract char draw();
	
}
