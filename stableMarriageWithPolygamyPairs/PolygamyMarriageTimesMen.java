package stableMarriageWithPolygamyPairs;

import java.util.ArrayList;
import java.util.HashMap;


/*
 * All men get to choose multiple women
 * man proposes, if woman is married she ranks and marries the man
 * if woman rejects then man proposes until married
 * men propose totalMen times
 * 
 * */
public class PolygamyMarriageTimesMen {
	private int[][] menPref;
	/*
	 * 
	 */

	private int[][] womenPref;

	/*
	 * 
	 */

	private int totalMen;
	private int [] lastMarried;

	private ArrayList<String> pairs;
	private HashMap<Integer, Integer> map;

	private boolean[] marriedWomen; // if married, tru
	private int[] pair;
	// private boolean[] marriedMen; // if married, tru
	// private int singles;
	int freeCount = totalMen; // Initialize all men and women as free

	public PolygamyMarriageTimesMen(int[][] m, int[][] w) {
		map = new HashMap<>();
		totalMen = m.length;
		lastMarried = new int [totalMen*2];
		// pairs = new ArrayList<>();
		pair = new int[totalMen * 2];
		marriedWomen = new boolean[totalMen * 2];
		// marriedMen = new boolean[totalMen];
		menPref = new int[totalMen][totalMen * 2];
		womenPref = new int[totalMen * 2][totalMen];
		copyArrayM(m, menPref);
		copyArrayW(w, womenPref);
		// singles = 0;

		
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

	public void alg() {
		int count = 0;
		while(count < totalMen) {
			 
			int m;
			for (m = 0; m < totalMen; m++) { // pick a man
				// System.out.println("m = " + m);
				for (int i = lastMarried[m]; i < totalMen *  2; i++) {
					 //System.out.println("i = " + i);
					int partner = menPref[m][i]; // pick the preference\
					 //System.out.println("partner = " + partner);
					if (pair[partner - 1] != m + 1) {
						if (pair[partner - 1] != 0) {// check if they are married

							// if yes married
							 //System.out.println("the woman is married");

							if (checkIfPrefersProposalOverPartner(partner, m)) {
								// System.out.println("the woman prefers proposal");
								// if yes
								// marry the two and
								// unmarry the original partner
								marry(partner, m, i);
								 //System.out.println("partners " + pair[partner-1] + ","+ partner);
								 //System.out.println("man " + pair[partner-1]);
								break;

							}
						} else { // if not married
							// marry the two
							 //System.out.println("woman never married");
							marry(partner, m, i);
							//System.out.println("partners " + pair[partner-1] + ","+ partner);
							 //System.out.println("partner " + partner);
							 //System.out.println("man " + pair[partner-1]);
							break;
						}

					}
				}
			}
			count++;
			
		}
	}

	private void marry(int partner, int m, int index) {
		lastMarried[m] = index+1;
		marriedWomen[partner - 1] = true;
		pair[partner - 1] = m + 1;
	}

	private boolean checkIfPrefersProposalOverPartner(int partner, int m) {
		int rankOfProposal = findRank(partner, m+1);
		//System.out.println("rankOfProposal " + rankOfProposal);
		int rankOfCurrentPartner = findRank(partner, pair[partner - 1]);
		//System.out.println("rankOfCurrentPartner " + rankOfCurrentPartner);
		if (rankOfProposal < rankOfCurrentPartner)
			return true;
		else
			return false;
	}

	private int findRank(int woman, int man) {
		int pref[] = womenPref[woman - 1];
		//int j = 0;
		for (int j = 0; j < pref.length; j++) { // for all men in choiced woman's list
			if (pref[j] == man) { // if that man exists
				//System.out.println(pref[j]);
				return j; // return the index of that man's existence
			}
		}

		return 0;
	}

	public void printCouples() {
		for (int i = 0; i < pair.length; i++) {
			if (pair[i] != 0)
				System.out.println("man " + pair[i] + ", woman " + (i + 1));

		}
	}
}
