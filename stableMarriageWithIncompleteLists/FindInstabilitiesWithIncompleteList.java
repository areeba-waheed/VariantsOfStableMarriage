package stableMarriageWithIncompleteLists;

import java.util.ArrayList;

//On3
public class FindInstabilitiesWithIncompleteList {
	private int[][] men;
	private int[][] women;
	private int[][] pairs;
	private ArrayList<String> unstablePairs;
	private int instabilities;

	public FindInstabilitiesWithIncompleteList(int[][] men, int[][] women, int[] pairs) {
		this.men = men;
		this.women = women;
		this.pairs = new int[men.length][2];
		addPairs(pairs);
		unstablePairs = new ArrayList<>();
		instabilities = 0;
	}

	private void addPairs(int[] p) {
		for (int i = 0; i < p.length; i++) {
			pairs[i][0] = i + 1;
			pairs[i][1] = p[i];
		}
	}

	public void findInstabilties() {
		for (int i = 0; i < pairs.length; i++) { // for each pair of men
//System.out.println(" i  " + i);
			int manOfHonor = pairs[i][0];// get the man
			int partnerOfMan = pairs[i][1]; // get his man
//System.out.println(" manOfHonor " + manOfHonor);
//System.out.println(" partnerOfMan " +partnerOfMan);
			int rank = findRank(manOfHonor, partnerOfMan, this.men);// find the rank of the partner of man
//System.out.println(" rank " + rank);
			for (int j = 0; j < rank; j++) {
//System.out.println("j " + j);
				int womanOfChoice = men[i][j];
//System.out.println(" womanOfChoice " + womanOfChoice);
				if (partnerOfMan == 0)
					break;
				int womanPartner = findWomanPartner(womanOfChoice);
//System.out.println(" womanPartner " + womanPartner);
				boolean instability = womanPrefersManOverPartner(manOfHonor, womanOfChoice, womanPartner);
				if (instability) {
					instabilities++;
					unstablePairs.add("{" + manOfHonor + ", " + partnerOfMan + "}");
					System.out.println(" unstablePairs " + unstablePairs);
					break;
				}
			}

		}

	}

	public int getInstabilities() {
		return this.instabilities;
	}

	private int findWomanPartner(int woman) {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][1] == woman) {
				return pairs[i][0];
			}
		}
		return 0;
	}

	private boolean womanPrefersManOverPartner(int man, int woman, int womanPartner) {
		int rankOfMan = findRank(woman, man, women);
		int rankOfCurrentPartner = findRank(woman, womanPartner, women);
		if (rankOfMan < rankOfCurrentPartner)
			return true;
		else
			return false;

	}

	private int findRank(int man, int woman, int[][] array) {
		int m[] = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			m[i] = array[man - 1][i];
		}
		for (int j = 0; j < m.length; j++) { // for all women in choice
////System.out.println(" m " + j + " " + m[j]); // woman's list
			if (m[j] == woman) { // if that woman exists

				return j; // return the index of that woman's existence
			}
		}

		return 0;
	}

	/*
	 * public static void main(String[] args) { int m [][] = { {3,0, 0}, {1,2,0},
	 * {3,1,2}}; int w [][] = { {2,3,0}, {2,3,0}, {3,2,1}}; /*int p [][] = { {1,1},
	 * {2,2}, {3,3}, {4,4}}; int p [] = {3,2,1}; FindInstabilities f = new
	 * FindInstabilities(m,w,p); f.findInstabilties();
	 * System.out.println(f.getInstabilities()); }
	 */
}