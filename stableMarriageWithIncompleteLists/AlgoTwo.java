package stableMarriageWithIncompleteLists;

import java.util.ArrayList;

//strikes are n/4
public class AlgoTwo {
	private int[][] menPref;

	private int[][] womenPref;

	private ArrayList<Integer> menSingles;

	private boolean[] womenSingles;

	private int[] womenPartner;
	private int[] menPartner;

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
		menPartner = new int[totalMen];
		menMarried = new boolean[totalMen];
		womenStrikes = new int[totalMen];
		addStrikes();
		algorithm();
	}

	public int[] getWomenPartner() {
		return this.womenPartner;
	}

	private void addStrikes() {
		for (int i = 0; i < womenStrikes.length; i++) {
			womenStrikes[i] = totalMen / 4;
		}
	}

	public void algorithm() {
		int menAvailable = totalMen;
//system.out.println(menAvailable);
		while (menAvailable >= 1) {

			for (int i = 0; i < totalMen; i++) {
//system.out.println("menAvqailable " + menAvailable);
//system.out.println("i " + i);
				if (!menMarried[i]) { // false meaning single
					for (int j = 0; j < totalMen; j++) {
//if choice ==0, put man in singles, break out and go
//to next man
//system.out.println("j = " + j);
						int choice = menPref[i][j];
						if (choice == 0) {
//system.out.println("men is single now");
							menSingles.add(i + 1);
							menMarried[i] = true;
							menAvailable--;
							break;
						} else // if choice exists

//if woman single and has the man in the list, engage
//them
						if (!womenSingles[choice - 1] && manInWomansList(i + 1, choice)) { // means
//single
							womenSingles[choice - 1] = true;
							menMarried[i] = true;
							womenPartner[choice - 1] = i + 1;
//menPartner[i] = choice;
							menAvailable--;
//system.out.println("married");
							break;
						} else if (womenSingles[choice - 1] && manInWomansList(i + 1, choice)) { // if woman
//engaged
//system.out.println("choosing between partners");
//check for her strikes
							if (gotStrikes(choice)) {
//system.out.println("here4");
//rank partner and proposer
								int rankPartner = rank(womenPartner[choice - 1], choice);
								int rankProposal = rank(i + 1, choice);

//if prefers proposal, engage them
								if (rankPartner > rankProposal) {
//system.out.println("proposal won");
									womenSingles[choice - 1] = true;
									menMarried[i] = true;
									menMarried[womenPartner[choice - 1] - 1] = false;
									womenPartner[choice - 1] = i + 1;
//menPartner[i] = choice;

									break;
								}
							}
						}
						if (j == totalMen - 1) {
//system.out.println("end of i");
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
//list
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
//list
			if (womenPref[woman - 1][j] == man) { // if that man exists
////system.out.println(j);
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
				System.out.println("man " + womenPartner[i] + ", woman " + (i + 1));
			}
		}
	}

	public void printSingles() {
		System.out.println(menSingles);
	}

	public int getSingles() {
		// TODO Auto-generated method stub
		return this.menSingles.size();
	}
}