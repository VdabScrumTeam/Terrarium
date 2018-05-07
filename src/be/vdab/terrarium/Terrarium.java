package be.vdab.terrarium;

import java.util.Random;

public class Terrarium {

	private int rows = 6;
	private int cols = 6;
	private Organism[][] organisms = new Organism[rows][cols];
	
	
	public Terrarium() {
		init();
	}
	
	private void init() {
		//Fill array with empty organisms
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				Coordinate coordinate = new Coordinate(i,j);
				organisms[i][j] = new EmptyOrganism(coordinate);
			}
		}
		//toevoegen van 2 planten, 2 herbivoren en 2 carnivoren
		try {
			add(new Plant(getFreeLocation()));
			add(new Plant(getFreeLocation()));
			add(new Herbivore(getFreeLocation()));
			add(new Herbivore(getFreeLocation()));
			add(new Carnivore(getFreeLocation()));
			add(new Carnivore(getFreeLocation()));
		}catch(IllegalArgumentException e) {
			
		}
		
	}
	
	public void nextDay() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols-1; j++) {
				//System.out.println(i + ", " + j);
				if(organisms[i][j] instanceof Herbivore) {
					if(organisms[i][j+1] instanceof Herbivore) {
						letThemMakeLove();
					}
					if(organisms[i][j+1] instanceof Plant) {
						letItEat(organisms[i][j], organisms[i][j+1]);
					}
					if(organisms[i][j+1] instanceof EmptyOrganism) {
						move(organisms[i][j]);
					}
				}
				if(organisms[i][j] instanceof Carnivore) {
					if(organisms[i][j+1] instanceof Herbivore) {
						letItEat(organisms[i][j], organisms[i][j+1]);
					}
					if(organisms[i][j+1] instanceof Carnivore) {
						letThemFight(organisms[i][j], organisms[i][j+1]);
					}
					if(organisms[i][j+1] instanceof EmptyOrganism) {
						move(organisms[i][j]);
					}
				}
			}
		}
		try {
			add(new Plant(getFreeLocation()));
			add(new Plant(getFreeLocation()));
			add(new Plant(getFreeLocation()));
		}catch(IllegalArgumentException e) {
			
		}
		
		
	}
	
	private void letItEat(Organism eater, Organism lunchOnTheRight) {
		if (eater instanceof Herbivore && lunchOnTheRight instanceof Plant) {
			eater.lifeforce += lunchOnTheRight.lifeforce;
			delete(lunchOnTheRight);
		} else if (eater instanceof Carnivore && lunchOnTheRight instanceof Herbivore) {
				eater.lifeforce += lunchOnTheRight.lifeforce;
				delete(lunchOnTheRight);
		} else {
			// niks te eten
		}
	}
	
	private void letThemFight(Organism fighterOnTheLeft, Organism fighterOnTheRight) {
		if(fighterOnTheLeft.getLifeforce() > fighterOnTheRight.getLifeforce()) {
			fighterOnTheLeft.setLifeforce(fighterOnTheLeft.getLifeforce() + fighterOnTheRight.getLifeforce());
		}else if(fighterOnTheRight.getLifeforce() > fighterOnTheLeft.getLifeforce()){
			fighterOnTheRight.setLifeforce(fighterOnTheLeft.getLifeforce() + fighterOnTheRight.getLifeforce());
		}
	}
		
	private void letThemMakeLove() {
		add(new Herbivore(getFreeLocation()));
	}
	
	
	private Coordinate getFreeLocation() {
		//Vrije locatie in de array vinden
		int teller = 0;
		Coordinate coordinate;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(organisms[i][j] instanceof EmptyOrganism) {
					teller++;
				}
			}
		}
		
		if(teller == 0) {
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
		if(org != null) {
			organisms[org.getCoordinate().getRow()][org.getCoordinate().getCol()] = org;
		}
		
	}
	
	public void draw() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				System.out.print(organisms[i][j].draw() + "   ");
			}
			System.out.println();
		}
	}
	
	public void move(Organism orgaantje) {
		boolean moved = false;
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
						organisms[orgaantje.getCoordinate().getRow()-1][orgaantje.getCoordinate().getCol()] = orgaantje;
						organisms[orgaantje.getCoordinate().getRow()-1][orgaantje.getCoordinate().getCol()].setCoordinate(north.getCoordinate());
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 2:
					if (orgaantje.getCoordinate().getCol() == 5) { break; }
					Organism east = organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()+1];
					organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()+1] = orgaantje;
					organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()+1].setCoordinate(east.getCoordinate());
					delete(orgaantje);
					moved = true;
					break;
				case 3:
					if (orgaantje.getCoordinate().getRow() == 5) { break; }
					Organism south = organisms[orgaantje.getCoordinate().getRow()+1][orgaantje.getCoordinate().getCol()];
					if (south instanceof EmptyOrganism) {
						organisms[orgaantje.getCoordinate().getRow()+1][orgaantje.getCoordinate().getCol()] = orgaantje;
						organisms[orgaantje.getCoordinate().getRow()+1][orgaantje.getCoordinate().getCol()].setCoordinate(south.getCoordinate());
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 4:
					if (orgaantje.getCoordinate().getCol() == 0) { break; }
					Organism west = organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()-1];
					if (west instanceof EmptyOrganism) {
						organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()-1] = orgaantje;
						organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()-1].setCoordinate(west.getCoordinate());
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
			}
		}
	}
	
	public void delete(Organism org) {
		// TODO
		Coordinate c = org.getCoordinate();
		organisms[c.getRow()][c.getCol()] = new EmptyOrganism(c);
		
	}

}
