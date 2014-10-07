/**
 * A program to test the Cannonball class.
 * @author Matt Hynes
 */

import java.util.Scanner;

public class CannonballTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the starting angle: ");
		double a = in.nextDouble();
		System.out.println("Enter the initial velocity: ");
		double vi = in.nextDouble();
		Cannonball cb = new Cannonball(0);
		cb.shoot(a, vi);
	}

}
