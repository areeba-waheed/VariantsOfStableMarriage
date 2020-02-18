package stableMarriageWithIncompleteLists;

import java.util.ArrayList;

//strikes are n/4

public class AlgoTwo {

	private int[][] menPref;

	private int[][] womenPref;

	private ArrayList<Integer> menSingles;

	private boolean[] womenSingles;

	private int[] womenPartner;

	private int totalMen;

	private boolean[] menMarried;

	private int[] womenStrikes;

	public AlgoTwo(int[][] m, int[][] w) {

		menPref = m;

		womenPref = w;

		totalMen = m.length;

		menSingles = new ArrayList<>();

		womenSingles = new boolean[totalMen]; // single false

		womenPartner = new int[totalMen];

		menMarried = new boolean[totalMen];

		womenStrikes = new int[totalMen];

		addStrikes();

	}

	private void addStrikes() {

		for (int i = 0; i < womenStrikes.length; i++) {

			womenStrikes[i] = totalMen / 4;

		}

	}

	public void algorithm() {

		int menAvailable = totalMen;

		//System.out.println(menAvailable);

		while (menAvailable >= 1) {

			for (int i = 0; i < totalMen; i++) {

				//System.out.println("menAvqailable " + menAvailable);

				//System.out.println("i " + i);

				if (!menMarried[i]) { // false meaning single

					for (int j = 0; j < totalMen; j++) {

						// if choice ==0, put man in singles, break out and go

						// to next man

						//System.out.println("j = " + j);

						int choice = menPref[i][j];

						if (choice == 0) {

							//System.out.println("men is single now");

							menSingles.add(i + 1);

							menMarried[i] = true;

							menAvailable--;

							break;

						} else // if choice exists

						// if woman single and has the man in the list, engage

						// them

						if (!womenSingles[choice - 1]

								&& manInWomansList(i + 1, choice)) { // means

							// single

							womenSingles[choice - 1] = true;

							menMarried[i] = true;

							womenPartner[choice - 1] = i + 1;

							menAvailable--;

							//System.out.println("married");

							break;

						} else if (womenSingles[choice - 1]

								&& manInWomansList(i + 1, choice)) { // if woman

							// engaged

							//System.out.println("choosing between partners");

							// check for her strikes

							if (gotStrikes(choice)) {

								//System.out.println("here4");

								// rank partner and proposer

								int rankPartner = rank(

										womenPartner[choice - 1], choice);

								int rankProposal = rank(i + 1, choice);

								// if prefers proposal, engage them

								if (rankPartner > rankProposal) {

									//System.out.println("proposal won");

									womenSingles[choice - 1] = true;

									menMarried[i] = true;

									menMarried[womenPartner[choice - 1] - 1] = false;

									womenPartner[choice - 1] = i + 1;

									break;

								}

							}

						}
						if (j == totalMen - 1) {

							//System.out.println("end of i");

							menSingles.add(i + 1);

							menMarried[i] = true;

							menAvailable--;

							break;

						}

					}

				}

			}

		}

	}

	private boolean manInWomansList(int man, int woman) {

		int pref[] = womenPref[woman - 1];

		for (int j = 0; j < pref.length; j++) { // for all men in choiced woman

			// list

			if (pref[j] == man) { // if man exists

				return true;

			} else if (pref[j] == 0)

				return false; // if man exist but negative

		}

		return false;

	}

	private int rank(int man, int woman) {

		int result = 0;

		for (int j = 0; j < totalMen; j++) { // for all men in choiced woman's

			// list

			if (womenPref[woman - 1][j] == man) { // if that man exists

				// //System.out.println(j);

				return j + 1; // return the index of that man's existence

			}

		}

		return result;

	}

	private boolean gotStrikes(int choice) {

		if (womenStrikes[choice - 1] != 0) {

			womenStrikes[choice - 1]--;

			return true;

		}

		return false;

	}

	public void printCouples() {

		for (int i = 0; i < womenPartner.length; i++) {

			if (womenPartner[i] != 0) {

				//System.out.println("man " + womenPartner[i] + ", woman "

						//+ (i + 1));

			}

		}

	}

	public void printSingles() {

		//System.out.println(menSingles);

	}

}

/***
 * import java.util.ArrayList; //strikes are n/4 public class AlgoTwo { private
 * int[][] menPref;
 * 
 * private int[][] womenPref;
 * 
 * private ArrayList<Integer> menSingles;
 * 
 * private boolean[] womenSingles;
 * 
 * private int[] womenPartner;
 * 
 * private int totalMen;
 * 
 * private boolean[] menMarried;
 * 
 * private int[] womenStrikes;
 * 
 * public AlgoTwo(int [][] m, int [][] w){ menPref = m; womenPref = w; totalMen
 * = m.length; menSingles = new ArrayList<>(); womenSingles = new
 * boolean[totalMen]; //single false womenPartner = new int [totalMen];
 * menMarried = new boolean [totalMen]; womenStrikes = new int[totalMen];
 * addStrikes(); }
 * 
 * private void addStrikes() { for(int i =0; i<womenStrikes.length; i++) {
 * womenStrikes[i] = totalMen/4; } }
 * 
 * public void algorithm() { int menAvailable = totalMen; while(menAvailable >=
 * 1) {
 * 
 * for(int i = 0; i<totalMen; i++) {
 * 
 * if(!menMarried[i]) { //false meaning single for(int j =0; j<totalMen; j++) {
 * //if choice ==0, put man in singles, break out and go to next man
 * 
 * int choice = menPref[i][j]; if(choice == 0) { menSingles.add(i+1);
 * menMarried[i] = true; menAvailable--; break; } else { //if choice exists
 * 
 * //if woman single and has the man in the list, engage them
 * if(!womenSingles[choice-1] && manInWomansList(i+1, choice)) { //means single
 * womenSingles[choice-1] = true; menMarried[i] = true; womenPartner[choice-1] =
 * i + 1; menAvailable--; break; }else if(womenSingles[choice-1] &&
 * manInWomansList(i+1, choice)) { //if woman engaged
 * 
 * //check for her strikes if(gotStrikes(choice)) {
 * 
 * //rank partner and proposer int rankPartner = rank(womenPartner[choice-1],
 * choice); int rankProposal = rank(i+1, choice);
 * 
 * //if prefers proposal, engage them if(rankPartner > rankProposal) {
 * womenSingles[choice-1] = true; menMarried[i] = true;
 * menMarried[womenPartner[choice-1] - 1] = false; womenPartner[choice-1] = i +
 * 1; break; } } } } } } }
 * 
 * } }
 * 
 * private boolean manInWomansList(int man, int woman) { int pref [] =
 * womenPref[woman-1]; for(int j =0; j < pref.length; j++) { //for all men in
 * choiced woman list if(pref[j] == man) { //if man exists return true; } else
 * if(pref[j] == 0) return false; //if man exist but negative }
 * 
 * return false; }
 * 
 * private int rank(int man, int woman) { int result = 0; for(int j = 0; j
 * <totalMen; j++) { //for all men in choiced woman's list
 * if(womenPref[woman-1][j] == man) { //if that man exists
 * ////System.out.println(j); return j+1; //return the index of that man's
 * existence } }
 * 
 * return result; }
 * 
 * private boolean gotStrikes(int choice) { if(womenStrikes[choice - 1] != 0) {
 * womenStrikes[choice - 1]--; return true; } return false; }
 * 
 * public void printCouples() { for(int i =0; i<womenPartner.length; i++) {
 * if(womenPartner[i] != 0) { //System.out.println("man "+ womenPartner[i] + ",
 * woman " + (i+1)); } } }
 * 
 * public void printSingles() { //System.out.println(menSingles); } }
 **/
