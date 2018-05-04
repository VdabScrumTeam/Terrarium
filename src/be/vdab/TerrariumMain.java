package be.vdab;

import be.vdab.terrarium.Terrarium;

public class TerrariumMain {

	public static void main(String[] args) {
		Terrarium terr = new Terrarium();
		//System.out.println(terr.getFreeLocation()[0] + ", " + terr.getFreeLocation()[1]);
		terr.draw();
	}

}
