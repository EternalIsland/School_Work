import java.util.Scanner;

/**
 * A class to test the ComboLock program.
 * @author Matt Hynes
 */

public class ComboLockTest {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		int secret1 = sc.nextInt();
		int secret2 = sc.nextInt();
		int secret3 = sc.nextInt();
		
		ComboLock cl = new ComboLock(secret1, secret2, secret3);
		
		cl.reset();
		cl.turnRight(40 - 32);
		cl.turnLeft(8 + 24);
		cl.turnRight(24 - 16);
		cl.open();
	}

}
