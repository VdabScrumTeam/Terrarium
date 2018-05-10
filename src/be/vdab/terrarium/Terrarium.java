package be.vdab.terrarium;

import java.util.Random;

public class Terrarium {

	private int rows = 6;
	private int cols = 6;
	private Organism[][] organisms = new Organism[rows][cols];
	private int organismCount = 0;
	private int dayCount = 1;
	
	
	public Terrarium() {
		init();
	}
	
	public Organism[][] getOrganisms() {
		return organisms;
	}
	
	private void init() {
		//Fill array with empty organisms
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				Coordinate coordinate = new Coordinate(i,j);
				organisms[i][j] = new EmptyOrganism(coordinate);
			}
		}
		
		//add 2 plants, 2 herbivores and 2 carnivores
		add(new Plant(getCoordinate()));
		add(new Plant(getCoordinate()));
		add(new Herbivore(getCoordinate()));
		add(new Herbivore(getCoordinate()));
		add(new Carnivore(getCoordinate()));
		add(new Carnivore(getCoordinate()));
			
	}
	
	public void nextDay() throws TerrariumException {
		
		//increase day counter
		this.dayCount++;
		
		//let them do the actions
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols-1; j++) {
				letItEat(organisms[i][j], organisms[i][j+1]);
				letThemMakeLove(organisms[i][j], organisms[i][j+1]);
				letThemFight(organisms[i][j], organisms[i][j+1]);
				move(organisms[i][j], organisms[i][j+1]);
			}
		}
		
		//Add three plants
		add(new Plant(getCoordinate()));
		add(new Plant(getCoordinate()));
		add(new Plant(getCoordinate()));
		
		//Throw Exception when terrarium is full
		if(this.organismCount >= (this.rows * this.cols) ) {
			throw new TerrariumException("\nTerrarium is volgelopen, programma wordt beëindigd");
		}
	}	
		
	private void letItEat(Organism eater, Organism lunchOnTheRight) {
		if (eater instanceof Herbivore && lunchOnTheRight instanceof Plant) {
			eater.setLifeforce(eater.getLifeforce() + lunchOnTheRight.getLifeforce());
			delete(lunchOnTheRight);
		} else if (eater instanceof Carnivore && lunchOnTheRight instanceof Herbivore) {
			eater.setLifeforce(eater.getLifeforce() + lunchOnTheRight.getLifeforce());
			delete(lunchOnTheRight);
		}
	}
	
	private void letThemFight(Organism fighterOnTheLeft, Organism fighterOnTheRight) {
		if(fighterOnTheLeft instanceof Carnivore && fighterOnTheRight instanceof Carnivore) {
			if(fighterOnTheLeft.getLifeforce() > fighterOnTheRight.getLifeforce()) {
				fighterOnTheLeft.setLifeforce(fighterOnTheLeft.getLifeforce() + fighterOnTheRight.getLifeforce());
			}else if(fighterOnTheRight.getLifeforce() > fighterOnTheLeft.getLifeforce()){
				fighterOnTheRight.setLifeforce(fighterOnTheLeft.getLifeforce() + fighterOnTheRight.getLifeforce());
			}
		}
	}
		
	private void letThemMakeLove(Organism lover1, Organism lover2) {
		if(lover1 instanceof Herbivore && lover2 instanceof Herbivore) {
			add(new Herbivore(getCoordinate()));
		}
	}
	
	public void move(Organism orgaantje, Organism orgaantje2) {
		if((orgaantje instanceof Herbivore || orgaantje instanceof Carnivore) & orgaantje2 instanceof EmptyOrganism) {
			boolean moved = false;
			Coordinate huidigePos = orgaantje.getCoordinate();
			Coordinate nieuwePos;
			while (!moved) {
				// random getal "richting" van 1-4
				Random rand = new Random();
				int richting = rand.nextInt(4)+1;
				// switch volgens "richting"
				switch (richting) {
					case 1:
						if (orgaantje.getCoordinate().getRow() == 0) { break; }
						Organism north = organisms[orgaantje.getCoordinate().getRow()-1][orgaantje.getCoordinate().getCol()];
						if (north instanceof EmptyOrganism) {
							nieuwePos = north.getCoordinate();
							swapOrganisms(huidigePos, nieuwePos);
							moved = true;
						} else {
							moved = false;
						}
						break;
					case 2:
						if (orgaantje.getCoordinate().getCol() == 5) { break; }
						Organism east = organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()+1];
						nieuwePos = east.getCoordinate();
						swapOrganisms(huidigePos, nieuwePos);
						moved = true;
						break;
					case 3:
						if (orgaantje.getCoordinate().getRow() == 5) { break; }
						Organism south = organisms[orgaantje.getCoordinate().getRow()+1][orgaantje.getCoordinate().getCol()];
						if (south instanceof EmptyOrganism) {
							nieuwePos = south.getCoordinate();
							swapOrganisms(huidigePos, nieuwePos);
							moved = true;
						} else {
							moved = false;
						}
						break;
					case 4:
						if (orgaantje.getCoordinate().getCol() == 0) { break; }
						Organism west = organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()-1];
						if (west instanceof EmptyOrganism) {
							nieuwePos = west.getCoordinate();
							swapOrganisms(huidigePos, nieuwePos);
							moved = true;
						} else {
							moved = false;
						}
						break;
				}
			}
		}
	}
	
	private boolean isLocationFree() {
		//Check if there is a free location within the organisms array
		int teller = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(organisms[i][j] instanceof EmptyOrganism) {
					teller++;
				}
			}
		}
		return (teller == 0) ? false : true;
	}
	
	private Coordinate getCoordinate() {
		//Return a coordinate of free location within terrarium. If full coordinate = -1,-1 
		Coordinate coordinate;
		if(!isLocationFree()) { 
			coordinate = new Coordinate(-1,-1);
		}else {
			Random rand = new Random();
			coordinate = new Coordinate(rand.nextInt(rows),rand.nextInt(cols));
			boolean isFound = false;
			while(!isFound) {
				if(organisms[coordinate.getRow()][coordinate.getCol()] instanceof EmptyOrganism ) {
					isFound = true;
					//System.out.println(coordinate[0]+ ", " + coordinate[1]);
				}else {
					coordinate = new Coordinate(rand.nextInt(rows),rand.nextInt(cols));
				}
			}
		}
		return coordinate;
	}
	
	private void add(Organism org) {
		//Only adds an organism to the terrarium if it has VALID coordinates (!= -1,-1)
		if(org != null && org.getCoordinate().getRow() != -1) {
			organisms[org.getCoordinate().getRow()][org.getCoordinate().getCol()] = org;
			//increase organism count
			this.organismCount++;
		}
	}
		
	public void draw() {
		System.out.println("Dag " + dayCount + ":");
		System.out.println("*********\n");
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.print(organisms[i][j].draw()+ "   ");
			}
			System.out.println();
		}
	}
		
	private void swapOrganisms(Coordinate c1, Coordinate c2) {
		//Swap objects
		Organism temp = organisms[c1.getRow()][c1.getCol()];
		organisms[c1.getRow()][c1.getCol()] = organisms[c2.getRow()][c2.getCol()];
		organisms[c2.getRow()][c2.getCol()] = temp;
		//change coordinates
		organisms[c1.getRow()][c1.getCol()].setCoordinate(c1);
		organisms[c2.getRow()][c2.getCol()].setCoordinate(c2);
	}
	
	public void delete(Organism org) {
		//delete organism from terrarium
		Coordinate c = org.getCoordinate();
		organisms[c.getRow()][c.getCol()] = new EmptyOrganism(c);
		//decrease organism count
		this.organismCount--;
	}

}
