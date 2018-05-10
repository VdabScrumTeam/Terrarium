package be.vdab;

import java.util.Scanner;
import be.vdab.terrarium.*;

public class TerrariumMain {

	public static void main(String[] args) {
		Terrarium terrarium = new Terrarium();
		String keuze;
		
		System.out.println("TERRARIUM");
		System.out.println("*********\n");
		
		terrarium.draw();
				
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("\nDruk <ENTER> voor een nieuwe dag of S en <ENTER> om te stoppen");
			keuze = sc.nextLine();
			while(!keuze.equalsIgnoreCase("S")) {
				if(keuze.isEmpty()) {
					try {
						terrarium.nextDay();
					}catch(TerrariumException e) {
						terrarium.draw();
						System.out.println(e.getMessage());
						break;
					}
					terrarium.draw();
					System.out.println("\nDruk <ENTER> voor een nieuwe dag of S en <ENTER> om te stoppen");
				}else {
					System.out.println("Foute keuze! Druk <ENTER> voor een nieuwe dag of S en <ENTER> om te stoppen");
				}
				keuze = sc.nextLine();
			}
		}
		
		System.out.println("\nProgramma werd beëindigd.");
	
	}

}
