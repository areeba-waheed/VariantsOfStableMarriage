package stableMarriageWithPolygamyPairs;

import java.util.ArrayList;

public class FindInstabilitiesForPolygamy {
	
	//instable if a woman prefers another man over her current partner and that man has less wives than current partner where that man also prefers her than his current partners 

	private int[][] men;
	private int[][] women;
	private int[][] pairs;
	private ArrayList<String> unstablePairs;
	private int instabilities;

	public FindInstabilitiesForPolygamy(int[][] men, int[][] women, int[][] pairs) {
		this.men = men;
		this.women = women;
		this.pairs = pairs;
		unstablePairs = new ArrayList<>();
		instabilities = 0;
	}

	public void findInstabilties() {
		for (int i = 0; i < men.length; i++) { // for each man
//System.out.println(" i  " + i);
			int manOfHonor = i+1;// get the man
			//find the number of women hes married too already
			int marriages = findNumberOfMarriages(manOfHonor);
			//loop through his choices
			for (int j = 0; j < women.length; j++) {
				//find the man's choice
				int choice = men[i][j];
				//find her current partner
				int currentPartner = findWomanPartner(choice);
				//check if he is already married to her, if yes break to next woman
				if(manAlreadyMarried(manOfHonor, choice)) {break;}
				//check if he likes her more than his current partners
				if(likesWomanMoreThanCurrentPartners(manOfHonor, choice)) {
					//check if the woman likes him more than his current partner
					if(likesManMoreThanCurrentPartner(manOfHonor, choice, currentPartner) ) {
						//check if the number of marriages of proposal is less than partner
						if(findNumberOfMarriages(currentPartner) > marriages) {
							instabilities++;
							unstablePairs.add("{" + manOfHonor + ", " + choice + "}");
							//System.out.println(" unstablePairs " + unstablePairs);
						}
					}
				}
			}

		}

	}



	private boolean likesWomanMoreThanCurrentPartners(int manOfHonor, int choice) {
		Boolean bool = false;
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][0] == manOfHonor) {
				int partner = pairs[i][1]; 
				int rankOfPartner = findRank(manOfHonor, partner, men);
				int rankOfChoice = findRank(manOfHonor, choice, men);
				if(rankOfPartner > rankOfChoice) {
					bool = true;
				}	
			}
		}
		return bool;
	}

	private boolean manAlreadyMarried(int manOfHonor, int choice) {
		Boolean bool = false;
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][0] == manOfHonor) {
				if(pairs[i][1] == choice) {
					bool = true;
				}
			}
		}
		return bool;
	}

	private int findNumberOfMarriages(int manOfHonor) {
		int count = 0;
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][0] == manOfHonor) {
				count++;
			}
		}
		return count;
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

	private boolean likesManMoreThanCurrentPartner(int man, int woman, int womanPartner) {
		int rankOfMan = findRank(woman, man, women);
		int rankOfCurrentPartner = findRank(woman, womanPartner, women);
		if (rankOfMan < rankOfCurrentPartner)
			return true;
		else
			return false;

	}

	private int findRank(int man, int woman, int[][] array) {
		int m[] = array[man - 1];
		for (int j = 0; j < m.length; j++) { // for all women in choice
////System.out.println(" m " + j + " " + m[j]); // woman's list
			if (m[j] == woman) { // if that woman exists

				return j; // return the index of that woman's existence
			}
		}

		return 0;
	}
	
	public static void main(String[] args) {
		
		int[][] m = {
				{2,3,4,1}, 
				{2,1,3,4}
				};
		
		int [][] w = {
				{1,2},
				{2,1},
				{1,2},
				{2,1}
		};
		
		int [][] p = {
				{1,2},
				{1,3},
				{1,4},
				{2,1}
		};
		FindInstabilitiesForPolygamy f = new FindInstabilitiesForPolygamy(m, w, p); 
		f.findInstabilties(); 
		System.out.println(f.getInstabilities()); 
		
	}

	/*
	 * public static void main(String[] args) { int m[][] = { { 1, 2, 3, 4 }, { 4,
	 * 1, 3, 2 }, { 2, 1, 3, 4 }, { 4, 1, 3, 2 } }; int w[][] = { { 3, 1, 2, 4 }, {
	 * 3, 1, 2, 4 }, { 4, 1, 2, 3 }, { 2, 4, 1, 3 } }; int p[][] = { { 1, 1 }, { 2,
	 * 2 }, { 3, 3 }, { 4, 4 } }; FindInstabilities f = new FindInstabilities(m, w,
	 * p); f.findInstabilties(); System.out.println(f.getInstabilities()); }
	 */
}
