package be.vdab.terrarium;

import java.util.Random;

public class Terrarium {

	private Organism[][] organisms = new Organism[6][6];
	
	@Override
	public char draw() {
		
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
					if (orgaantje.coordinate.row == 0) { break; }
					north = Organism[orgaantje.coordinate.row-1][orgaantje.coordinate.column];
					if north.instanceOf(EmptyOrganism) {
						Organism[orgaantje.coordinate.row-1][orgaantje.coordinate.column] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 2:
					Organism[orgaantje.coordinate.row][orgaantje.coordinate.column+1] = orgaantje;
					delete(orgaantje);
					moved = true;
					break;
				case 3:
					if (orgaantje.coordinate.row == 5) { break; }
					south = Organism[orgaantje.coordinate.row+1][orgaantje.coordinate.column];
					if south.instanceOf(EmptyOrganism) {
						Organism[orgaantje.coordinate.row+1][orgaantje.coordinate.column] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
				case 4:
					if (orgaantje.coordinate.column == 0) { break; }
					east = Organism[orgaantje.coordinate.row][orgaantje.coordinate.column+1];
					if east.instanceOf(EmptyOrganism) {
						Organism[orgaantje.coordinate.row][orgaantje.coordinate.column+1] = orgaantje;
						delete(orgaantje);
						moved = true;
					} else {
						moved = false;
					}
					break;
			}
		}
	}
}