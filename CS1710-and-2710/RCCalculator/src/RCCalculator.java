/**
 * A program that read parameters from a text file and
 * calculates the voltage of an electric circuit with a
 * capacitor and a resistor and then outputs the voltage
 * into another text file.
 * @author Matt Hynes
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RCCalculator {

	public static void main(String[] args) throws FileNotFoundException {
		
		int B = 0, R = 0, ti = 0, tf = 0, step = 0;
		double C = 0, volts;
		File parameters = new File("params.txt");
		Scanner sc = new Scanner(parameters);
		PrintWriter calculation = new PrintWriter("rc.txt");
		
		System.out.println("params.txt:");
		
		while(sc.hasNextLine()){ //simultaneously reads params.txt and outputs contents to console
			B = sc.nextInt();
			System.out.println("B = " + B);
			R = sc.nextInt();
			System.out.println("R = " + R);
			C = sc.nextDouble();
			System.out.println("C = " + C);
			ti = sc.nextInt();
			System.out.println("Starting time = " + ti);
			tf = sc.nextInt();
			System.out.println("Ending time = " + tf);
		}
		
		System.out.println("\nrc.txt:");
		while(ti < 100){ //simultaneously prints results to rc.txt and console
			volts = B*(1 - Math.exp(-ti/(R*C)));
			calculation.printf("%d %10.5f %n", ti, volts);
			System.out.printf("%d %10.5f %n", ti, volts);
			ti += (tf/100);
			step++;
		}
		sc.close();
		calculation.close();
	}
}