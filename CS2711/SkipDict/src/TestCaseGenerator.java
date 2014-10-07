/**
 * @author Matthew Hynes (201200318)
 * CS 2760 - Assignment 3
 * 
 * Generates test cases of size 10, 50, and 200 in order, randomly, and with multiples
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class TestCaseGenerator {

	public static void main(String[] args) throws FileNotFoundException {

		//testCaseRand();
		//testCaseOrder();
		testCaseCopy();
	}

	// random numbers of each size
	private static void testCaseRand() throws FileNotFoundException {
		Random r = new Random();
		PrintWriter out = new PrintWriter("200rand.txt");

		for (int i = 0; i < 200; i++) {
			out.print(r.nextInt(101) + " ");
		}
		out.close();

		out = new PrintWriter("50rand.txt");
		for (int i = 0; i < 50; i++) {
			out.println(r.nextInt(101) + " ");
		}
		out.close();

		out = new PrintWriter("10rand.txt");
		for (int i = 0; i < 10; i++) {
			out.println(r.nextInt(101) + " ");
		}
		out.close();

	}

	// numbers in order from 0 to x of each size
	private static void testCaseOrder() throws FileNotFoundException {
		PrintWriter out = new PrintWriter("order.txt");

		for (int i = 0; i < 200; i++) {
			out.print(i + " ");
		}

		out.println();

		for (int i = 0; i < 50; i++) {
			out.print(i + " ");
		}

		out.println();

		for (int i = 0; i < 10; i++) {
			out.print(i + " ");
		}

		out.close();
	}

	// numbers with some copies of each size
	private static void testCaseCopy() throws FileNotFoundException {
		Random r = new Random();
		PrintWriter out = new PrintWriter("copy.txt");

		for (int i = 0; i < 200; i++) {
			if (i % 5 == 0) {
				out.print(r.nextInt(5) + " ");
			} else {
				out.print(i + " ");
			}
		}

		out.println();

		for (int i = 0; i < 50; i++) {
			if (i % 5 == 0) {
				out.print(r.nextInt(3) + " ");
			} else {
				out.print(i + " ");
			}
		}

		out.println();

		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				out.print(r.nextInt(2) + " ");
			} else {
				out.print(i + " ");
			}
		}
		out.close();
	}
}
