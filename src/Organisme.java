import java.util.Random;

public abstract class Organisme implements Item {
	public void plaats(Organisme org, array[6][6] newArray) {
		boolean done = false;
		while (done == false) {
			// Draw a random position in a 6x6 array
			Random dice = new Random();
			int a = dice.nextInt(6);
			int b = dice.nextInt(6);
			// Check if position is Empty
			if (newArray[a][b].instanceOf(Empty)) {
				newArray[a][b] = org;
				done = true;
			} else {
				done = false;
			}
		}
	}
}