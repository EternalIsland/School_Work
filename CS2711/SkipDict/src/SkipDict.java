/**
 * @author Matthew Hynes (201200318)
 * CS 2711 - Assignment 3
 */

import java.util.ArrayList;
import java.util.Random;

public class SkipDict<K extends Comparable<K>, V> {

	private QuadNode head, tail;
	private Random r;
	private int h; // height of skip list
	private double p; // probability of heads

	public SkipDict() {
		head = new QuadNode(null, null, 0, null, null);
		tail = null;
		r = new Random();
		h = 0;
		p = 0.5;
	}

	private int newLevel() {
		int level = 0;

		// generate level of entry
		while (level <= h && r.nextDouble() < p) {
			level++;
		}

		return level;
	}

	public void put(K key, V value) {
		int lvl = newLevel();

		if (lvl > head.lvl) {
			head = new QuadNode(key, value, lvl, null, head);
		}

		QuadNode curr = head; // node currently being visited
		QuadNode last = null;

		while (curr != null) {
			if (curr.next == null || curr.next.key.compareTo(key) > 0) {
				if (lvl >= curr.lvl) {
					QuadNode q = new QuadNode(key, value, curr.lvl, curr.next,
							null);

					if (last != null) {
						last.down = q;
					}

					curr.next = q;
					last = q;
				}

				curr = curr.down;
				continue;
			} else if (curr.next.key.equals(key)) {

				QuadNode q = new QuadNode(key, value, curr.lvl, curr.next, null);

				// move past nodes with same key
				while (curr.next.key.equals(key)) {
					curr = curr.next;
				}

				// insert node after last node with identical key
				curr.next = q;
				curr = curr.down;
				continue;
			}

			curr = curr.next;
		}
		h++;
		tail = last;
	}

	public V remove(K key) {
		V value = null;

		QuadNode curr = head;
		while (curr != null) {
			if (curr.next == null || curr.next.key.compareTo(key) >= 0) {
				if (curr.next != null && curr.next.key.equals(key)) {
					// return first instance of key
					value = curr.next.value;
					curr.next = curr.next.next;
				}

				curr = curr.down;
				continue;
			}

			curr = curr.next;
		}

		h--;
		return value;
	}

	public int size() {
		return h;
	}

	public boolean isEmpty() {
		return h == 0;
	}

	private class QuadNode {
		private K key;
		private V value;
		public int lvl;
		public QuadNode next, down;

		public QuadNode(K key, V value, int lvl, QuadNode next, QuadNode down) {
			this.key = key;
			this.value = value;
			this.lvl = lvl;
			this.next = next;
			this.down = down;
		}
	}

	public V get(K key) {
		QuadNode curr = head;
		while (curr != null) {
			if (curr.next == null || curr.next.key.compareTo(key) > 0) {
				curr = curr.down;
				continue;
			} else if (curr.next.key.equals(key)) {
				return curr.next.value;
			}

			curr = curr.next;
		}

		return null;
	}

	public ArrayList<V> getAll(K key) {
		ArrayList<V> values = new ArrayList<>();

		while (tail != null) {
			values.add(tail.next.value);
			tail = tail.next;
		}
		return values;
	}

	public static void main(String[] args) {

	}

}
