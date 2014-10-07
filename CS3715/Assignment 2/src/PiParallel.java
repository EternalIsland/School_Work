/**
 * @author Matthew Hynes
 * 
 * PiParallel accepts two command line arguments: number of simulations and number of threads to use. 
 * It spins up the given number of PiCalc threads and gives each an even number of simulations to run and 
 * calculates the value of pi using the Monte Carlo method.
 */

import java.util.ArrayList;
import java.util.Random;

public class PiParallel {
	private static int simulations, threads;

	public static void main(String[] args) {
		long pStart = System.nanoTime();
		ArrayList<PiCalc> piCalcs = new ArrayList<>();
		System.out.println("Program start at: " + pStart);
		int sum = 0;

		simulations = Integer.parseInt(args[0]);
		threads = Integer.parseInt(args[1]);

		// split number of simulations oven number of threads
		simulations = simulations / threads;

		for (int i = 0; i < threads; i++) {
			PiCalc pc = new PiCalc(simulations);
			piCalcs.add(pc);
		}

		// start the threads
		for (PiCalc t : piCalcs) {
			t.start();
		}

		// join the threads to ensure pi is output correctly
		for (PiCalc t : piCalcs) {
			try {
				t.join();
				sum += t.getPi();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// average pi value
		System.out.println("Comined pi value: " + sum / threads);
		long pEnd = System.nanoTime();
		System.out.println("Program ending at: " + pEnd);
		System.out.println("Total time elapsed for program: " + (pEnd - pStart)
				+ "\n");
	}
}

class PiCalc extends Thread {
	private long startT, endT;
	private int sims;
	private double pi;

	public PiCalc(int sims) {
		this.sims = sims;
	}

	@Override
	public void run() {
		Random r = new Random();
		int inside = 0;
		int count = 0;
		int maxSims = sims;
		int simulations = maxSims;

		startT = System.nanoTime();
		System.out.println("Thread starting at: " + startT);

		do {
			// calculate pi repeatedly
			double d = Math.hypot(r.nextDouble(), r.nextDouble());
			if (d <= 1.0) {
				inside += 1;
			}
			count++;
			simulations--;
		} while (simulations > 0);
		pi = (4.0 * inside) / maxSims;
		System.out.printf("Approx. pi = %.12f%n", pi);

		endT = System.nanoTime();
		System.out.println("Thread finishing at: " + endT);
		System.out.println("Total time for thread: " + getElapsed());
	}

	public long getElapsed() {
		return endT - startT;
	}

	public long getStartT() {
		return startT;
	}

	public long getEndT() {
		return endT;
	}

	public double getPi() {
		return pi;
	}
}
