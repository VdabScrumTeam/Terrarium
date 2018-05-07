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
		add(new Plant(getFreeLocation()));
		add(new Plant(getFreeLocation()));
		add(new Herbivore(getFreeLocation()));
		add(new Herbivore(getFreeLocation()));
		add(new Carnivore(getFreeLocation()));
		add(new Carnivore(getFreeLocation()));
	}
	
	public void nextDay() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				
			}
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
		Random rand = new Random();
		Coordinate coordinate = new Coordinate(rand.nextInt(rows),rand.nextInt(cols));
		boolean isFound = false;
		while(!isFound) {
			if(organisms[coordinate.getRow()][coordinate.getCol()] instanceof EmptyOrganism ) {
				isFound = true;
				//System.out.println(coordinate[0]+ ", " + coordinate[1]);
			}else {
				coordinate = new Coordinate(rand.nextInt(rows),rand.nextInt(cols));
			}
		}
		return coordinate;
	}
	
	private void add(Organism org) {
		
		organisms[org.getCoordinate().getRow()][org.getCoordinate().getCol()] = org;
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
		while (!moved) {
			// random getal "richting" van 1-4
			Random rand = new Random();
			int richting = rand.nextInt(4)+1;
			// switch volgens "richting"
			boolean moved = false;
			switch (richting) {
				case 1:
					if (orgaantje.getCoordinate().getRow() == 0) { break; }
					Organism north = organisms[orgaantje.getCoordinate().getRow()-1][orgaantje.getCoordinate().getCol()];
					if (north instanceof EmptyOrganism) {
						organisms[orgaantje.getCoordinate().getRow()-1][orgaantje.getCoordinate().getCol()] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 2:
					organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()+1] = orgaantje;
					delete(orgaantje);
					moved = true;
					break;
				case 3:
					if (orgaantje.getCoordinate().getRow() == 5) { break; }
					Organism south = organisms[orgaantje.getCoordinate().getRow()+1][orgaantje.getCoordinate().getCol()];
					if (south instanceof EmptyOrganism) {
						organisms[orgaantje.getCoordinate().getRow()+1][orgaantje.getCoordinate().getCol()] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 4:
					if (orgaantje.getCoordinate().getCol() == 0) { break; }
					Organism east = organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()+1];
					if (east instanceof EmptyOrganism) {
						organisms[orgaantje.getCoordinate().getRow()][orgaantje.getCoordinate().getCol()+1] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
			}
		}
	}
	
	public void delete(Organism orgie) {
		// TODO
	}

}
