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
				organisms[i][j] = new EmptyOrganism();
			}
		}
		//toevoegen van 2 planten, 2 herbivoren en 2 carnivoren
		add(new Plant(), getFreeLocation());
		add(new Plant(), getFreeLocation());
		add(new Herbivore(), getFreeLocation());
		add(new Herbivore(), getFreeLocation());
		add(new Carnivore(), getFreeLocation());
		add(new Carnivore(), getFreeLocation());
	}
	
	private int[] getFreeLocation() {
				
		int[] coordinate = new int[2];
		Random rand = new Random();
		coordinate[0] = rand.nextInt(rows);
		coordinate[1] = rand.nextInt(cols);
		boolean isFound = false;
		while(!isFound) {
			if(organisms[coordinate[0]][coordinate[1]] instanceof EmptyOrganism ) {
				isFound = true;
				System.out.println(coordinate[0]+ ", " + coordinate[1]);
			}else {
				coordinate[0] = rand.nextInt(rows);
				coordinate[1] = rand.nextInt(cols);
			}
		}
		return coordinate;
	}
	
	public void add(Organism org, int[] coordinates) {
		organisms[coordinates[0]][coordinates[1]] = org;
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
		// random getal "richting" van 1-4
		Random rand = new Random();
		int richting = rand.nextInt(4)+1;
		// switch volgens "richting"
		boolean moved = false;
		while (!moved) {
			switch (n) {
				case 1:
					if (orgaantje.coordinates.rows == 0) { break; }
					Organism north = organisms[orgaantje.coordinates.rows-1][orgaantje.coordinates.cols];
					if (north.instanceOf(EmptyOrganism)) {
						organisms[orgaantje.coordinates.rows-1][orgaantje.coordinates.cols] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 2:
					organisms[orgaantje.coordinates.rows][orgaantje.coordinates.cols+1] = orgaantje;
					delete(orgaantje);
					moved = true;
					break;
				case 3:
					if (orgaantje.coordinates.rows == 5) { break; }
					Organism south = organisms[orgaantje.coordinates.rows+1][orgaantje.coordinates.cols];
					if (south.instanceOf(EmptyOrganism)) {
						organisms[orgaantje.coordinates.rows+1][orgaantje.coordinates.cols] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 4:
					if (orgaantje.coordinates.cols == 0) { break; }
					organisms east = Organism[orgaantje.coordinates.rows][orgaantje.coordinates.cols+1];
					if (east.instanceOf(EmptyOrganism)) {
						organisms[orgaantje.coordinates.rows][orgaantje.coordinates.cols+1] = orgaantje;
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