/** Matthew Hynes 
 * Input an array of integers and another integer, k, and the program will return the first two numbers it finds in the array that sum to k, if they exist.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class KSum {

	private static Scanner sc;
	private static ArrayList<Integer> alA;
	private static int k;
	private static String str;
	private static BufferedReader in;
	private static int diff;
	static String[] intA;

	public static void main(String[] args) {

		alA = new ArrayList<>();
		intA = new String[args.length];

		for (int i = 0; i < args.length; i++) {
			intA[i] = args[i];
		}

		for (String s : intA) {
			Integer.parseInt(s);
		}
		in = new BufferedReader(new InputStreamReader(System.in));

		try {
			str = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc = new Scanner(str);

		k = sc.nextInt();

		while (sc.hasNextInt()) {
			alA.add(sc.nextInt());
		}

		kSumMain(alA, k);
	}

	private static void kSumMain(ArrayList<Integer> A, int k) {
		int i = 0;
		int l = A.size();
		kSumRecusrive(A, k, l, i);
	}

	private static boolean kSumRecusrive(ArrayList<Integer> A, int k, int len,
			int index) {
		if (index > (len - 1)) {
			System.out.println("No such pair.");
			return false;
		}
		diff = k - A.get(index);
		if (A.contains(diff) && A.indexOf(diff) != index) {
			System.out.println("k = " + k);
			System.out.println("A = " + alA);
			System.out.println(A.get(index) + " at index [" + index + "]"
					+ " and " + diff + " at index [" + A.indexOf(diff) + "]");
			return true;
		} else {
			return kSumRecusrive(A, k, len, index + 1);
		}
	}

}
