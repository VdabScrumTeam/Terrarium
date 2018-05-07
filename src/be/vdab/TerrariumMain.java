package be.vdab;

import java.util.Scanner;

import be.vdab.terrarium.Terrarium;

public class TerrariumMain {

	public static void main(String[] args) {
		Terrarium terrarium = new Terrarium();
		System.out.println("Terrarium");
		terrarium.draw();
		String keuze;
		
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Druk <ENTER> voor een nieuwe dag of S en <ENTER> om te stoppen");
			keuze = sc.nextLine();
			while(!keuze.equalsIgnoreCase("S")) {
				if(keuze.isEmpty()) {
					System.out.println("A new day at the zoo!");
					terrarium.nextDay();
					terrarium.draw();
					
					System.out.println("Druk <ENTER> voor een nieuwe dag of S en <ENTER> om te stoppen");
				}else {
					
					System.out.println("Foute keuze! Druk <ENTER> voor een nieuwe dag of S en <ENTER> om te stoppen");
				}
				keuze = sc.nextLine();
			}
		}
		System.out.println("Programma werd beëindigd.");
		
		
	}

}
