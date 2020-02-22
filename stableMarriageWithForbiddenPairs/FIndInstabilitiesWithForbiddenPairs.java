package stableMarriageWithForbiddenPairs;

import java.util.ArrayList;

//On3
public class FIndInstabilitiesWithForbiddenPairs {
	private int[][] men;
	private int[][] women;
	private int[][] pairs;
	private ArrayList<String> unstablePairs;
	private int instabilities;

	public FIndInstabilitiesWithForbiddenPairs(int[][] men, int[][] women, int[] p) {
		this.men = men;
		this.women = women;
		this.pairs = new int [men.length][men.length];
		addPairs(p);
		unstablePairs = new ArrayList<>();
		instabilities = 0;
	}

	private void addPairs(int[] pairs2) {
		for(int i = 0; i< pairs2.length; i++) {
			//System.out.println(pairs2[i]);
			pairs[i][0] = i+1;
			pairs[i][1] = pairs2[i];
		}
		
	}

	public void findInstabilties() {
		for (int i = 0; i < pairs.length; i++) { // for each pair of men
//System.out.println(" i  " + i);
			int manOfHonor = pairs[i][0];// get the man
			int partnerOfMan = pairs[i][1]; // get his man
			if(partnerOfMan == 0) break;
//System.out.println(" manOfHonor " + manOfHonor);
//System.out.println(" partnerOfMan " +partnerOfMan);
			int rank = findRank(manOfHonor, partnerOfMan, this.men);// find the rank of the partner of man
//System.out.println(" rank " + rank);
			for (int j = 0; j < rank; j++) {
//System.out.println("j " + j);
				int womanOfChoice = men[i][j];
//System.out.println(" womanOfChoice " + womanOfChoice);
				if(womanOfChoice < 0) break;
				int womanPartner = findWomanPartner(womanOfChoice);
//System.out.println(" womanPartner " + womanPartner);
				boolean instability;
				if(womanPartner == 0) instability = true;
				else instability = womanPrefersManOverPartner(manOfHonor, womanOfChoice, womanPartner);
				if (instability) {
					instabilities++;
					unstablePairs.add("{" + manOfHonor + ", " + partnerOfMan + "}");
//System.out.println(" unstablePairs " + unstablePairs);
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
	  public static void main(String[] args) { 
		  int m[][] = { 
				  { 1, -2, -3, 4 },
				  { 4, -1, -3, 2 },
				  { -2, 1, -3, 4 }, 
				  { -4, 1, -3, -2 } }; 
		  int w[][] = {
				  { 3, 1, -2, 4 }, 
				  {3, 1, 2, -4 }, 
				  { 4, -1, -2, -3 }, 
				  { 2, 4, 1, 3 } }; 
		  int p[][] = { { 1, 4 }, { 2, 2 }, {3, 0}, { 4, 1 } }; 
		  FIndInstabilitiesWithForbiddenPairs f = new FIndInstabilitiesWithForbiddenPairs(m, w,
	 p); f.findInstabilties(); 
	 System.out.println(f.getInstabilities()); }
	 */
}
