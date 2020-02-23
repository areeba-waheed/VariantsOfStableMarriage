package stableMarriageWithPolygamyPairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PolygamyAlgorithmWIthRounds {
	private int[][] menPref;
	/*
	 * 
	 */

	private int[][] womenPref;

	/*
	 * round 1 women propose to all first choice, men accept unless they have been
	 * married to women in this round men propose to all its first choices women
	 * accept unless they are previously married
	 * 
	 * round 2 women propose to all second choice men accept unless thye have been
	 * married to women in this round men propose to all its second choice women
	 * accept unless previously married
	 * 
	 * ...
	 * 
	 * round totalMen women propose to all its nth choice men accept unless thye
	 * have been married to women in this round men propose to all its nth choice
	 * women accept unless previously married
	 */

	private int totalMen;
	private boolean[] lastMarried;

	private ArrayList<String> pairs;
	private HashMap<Integer, Integer> map;

	private boolean[] marriedWomen; // if married, tru
	private int[] pair;
	// private boolean[] marriedMen; // if married, tru
	// private int singles;
	int freeCount = totalMen; // Initialize all men and women as free

	public PolygamyAlgorithmWIthRounds(int[][] m, int[][] w) {
		map = new HashMap<>();
		totalMen = m.length;
		lastMarried = new boolean[totalMen];
		// pairs = new ArrayList<>();
		pair = new int[totalMen * 2];
		marriedWomen = new boolean[totalMen * 2];
		// marriedMen = new boolean[totalMen];
		menPref = new int[totalMen][totalMen * 2];
		womenPref = new int[totalMen * 2][totalMen];
		copyArrayM(m, menPref);
		copyArrayW(w, womenPref);
		alg();
		// singles = 0;

	}

	public void alg() {
		int round = 0;
		while (round != totalMen) { // loops until all women have been paired
			for (int i = 0; i < totalMen * 2; i++) {
				int choice = womenPref[i][round];
				// if woman is not married
				// and
				// man has not been chosen in this round
				if (!marriedWomen[i] && !lastMarried[choice - 1]) {
					marry(i + 1, choice);
				}

			}
			lastMarried = new boolean[totalMen];
			for (int i = 0; i < totalMen; i++) {
				int choice = menPref[i][round];
				// if woman is not married
				// and
				// man has not been chosen in this round
				if (!marriedWomen[choice - 1] && !lastMarried[i]) {
					marry(choice, i + 1);
				}
			}
			lastMarried = new boolean[totalMen];
			round++;
		}

	}

	private void marry(int woman, int man) {
		marriedWomen[woman - 1] = true;
		lastMarried[man - 1] = true;
		pair[woman - 1] = man;

	}
	
	public int [] getPairs() {return this.pair;}

	private void copyArrayM(int[][] old, int[][] current) {
		for (int i = 0; i < totalMen; i++) {
			for (int j = 0; j < totalMen * 2; j++) {
				current[i][j] = old[i][j];
				// System.out.print(current[i][j]);
			}
			// System.out.println();
		}
	}

	private void copyArrayW(int[][] old, int[][] current) {
		for (int i = 0; i < totalMen * 2; i++) {
			for (int j = 0; j < totalMen; j++) {
				current[i][j] = old[i][j];
				// System.out.print(current[i][j]);
			}
			// System.out.println();
		}
	}

	public void printCouples() {
		for (int i = 0; i < pair.length; i++) {
			if (pair[i] != 0)
				System.out.println("man " + pair[i] + ", woman " + (i + 1));

		}
	}
}
