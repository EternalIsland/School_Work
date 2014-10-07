/**
 * @author Matthew Hynes (201200318)
 * CS2760, 6 Nov 2013
 * 
 * This program reads a text file with each line formatted as "artist|year|album|track #|song"
 * and sorts it lexicographically using radix sort
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlaylistSorter {

	private static BufferedReader rd;
	private static String line, name, album, song;
	private static String[] finfo;
	private static int year, track;
	private static ArrayList<Object> ar;

	public static <V, K> void main(String[] args) {
		Map<List<Object>, V> info = new LinkedHashMap<>();
		ar = new ArrayList<>();

		try {
			rd = new BufferedReader(new FileReader(args[0]));

			while ((line = rd.readLine()) != null) {
				finfo = line.split("\\|");
				ar.clear();

				name = finfo[0];
				year = Integer.parseInt(finfo[1]);
				album = finfo[2];
				track = Integer.parseInt(finfo[3]);
				song = finfo[4];

				ar.add(name);
				ar.add(year);
				ar.add(album);
				ar.add(track);
				ar.add(song);

				info.put(ar, null);

				System.out.println("ar: " + ar); // contents of array
				System.out.println("info: " + info); // contents of map
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		musicSort(info, info.size());

	}

	/**
	 * Radix sort algorithm
	 * 
	 * @param info
	 * @param N
	 */
	private static <V> void musicSort(Map<List<Object>, V> info, int N) {

		for (int i = 4; i > 0; i--) {
			bucketSort(info, N);
		}
	}

	/**
	 * Bucket sort alogrithm taken from notes and modified slightly
	 * 
	 * @param info
	 * @param N
	 */
	private static <V> Map<List<Object>, V> bucketSort(
			Map<List<Object>, V> info, int N) {
		ArrayList<Object> bucket = new ArrayList<>();
		Object[] keys = info.keySet().toArray();
		int i = 0;

		while (!info.isEmpty() && i < keys.length) {
			bucket.add(keys[i]);
			keys[i] = null;
			info.remove(keys[i]);
			i++;
		}

		for (int ii = 0; ii < N - 1; ii++) {
			while (!bucket.isEmpty()) {
				info.put((List<Object>) bucket.remove(ii), null);
			}
		}

		return info;
	}
}
