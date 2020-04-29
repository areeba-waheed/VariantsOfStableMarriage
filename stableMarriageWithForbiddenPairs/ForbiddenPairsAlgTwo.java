package stableMarriageWithForbiddenPairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;
public class ForbiddenPairsAlgTwo {
	/*man proposes to his first two choices, check if forbidden, 
	 * if both forbidden, this man goes to singles list,
	 * if one is forbidden, then consider this woman to marry
	 * 		by considering, if shes unmarried or if she preferes proposal over her current partner, then they both marry
	 * if both are not forbidden then both rank their preference and woman with higher preference gets he guy unless she is married and preferes her husband over the propsal, then the other woman gets the guy*/

    private int[][] menPref;

    private int[][] womenPref;

    private ArrayList<Integer> menSingles;

    private ArrayList<Integer> womenSingles;

    private int[] womenPartner;
    
    private int totalMen;
    
    private boolean [] marriedWomen; //if married, tru
    
    public ForbiddenPairsAlgTwo(int [][] m, int [][] w){
    	 menPref = m;
    	 womenPref = w;
    	 totalMen = m.length;
    	 menSingles = new ArrayList<>(); 
    	 womenSingles = new ArrayList<>();  
    	 womenPartner = new int [totalMen]; 
    	 marriedWomen = new boolean[totalMen];
    	 matchMaker();
	
    }
    public int [] getWomenPartner() {return this.womenPartner;}
    /*assuming the input should be at least two members */
    public void matchMaker() {
    	/*WHILE THE MEN LAST*/
    	for(int i = 0; i<totalMen; i++) {
    		int firstChoice = menPref[i][0];
			int secondChoice = menPref[i][1];
			//if both not forbidden 
    		if(notForbidden(firstChoice, i+1) && notForbidden(secondChoice, i+1)) {
    			
    			//find rank
    			int firstWomanRank = findRank(firstChoice, i+1);
    			int secondWomanRank = findRank(secondChoice, i+1);
    			
    			if(firstWomanRank < secondWomanRank ) { //first choice is prefer
    				considerWithChoice(firstChoice, secondChoice, i+1);
    				
    				
    				
    			}else if (secondWomanRank < firstWomanRank ) {
    				
    				considerWithChoice(secondChoice, firstChoice, i+1);
    				
    				
    			}else { //if same ranks, consider man's first choice
    				
    				consider(firstChoice, i+1);
    			}
    			
    		}
    		else if(notForbidden(firstChoice, i+1)) { //if second is forbidden
    			consider(firstChoice, i+1);
    		}
    		else if(notForbidden(secondChoice, i+1)) { //if first is forbidden
    			consider(secondChoice, i+1);
    		}
    		else { //if both forbidden then that man stays single
    			menSingles.add(i+1);
    		}
    		
    	}
    }
    
    public void printCouples() {
    	System.out.println("Forbidden AglTwo Pairs: ");
    	for(int i =0; i<womenPartner.length; i++) {
    		if(womenPartner[i] != 0) {
    			System.out.println("man "+ womenPartner[i] + ", woman " + (i+1));
    		}
    	}
    }

    public void printSingles() {
    	System.out.println(menSingles);
    }

    /*CHECK IF WOMAN MARRIED AND PREFER HIM, IF NOT THEN GIVE THE OTHER WOMAN A CHANCE*/
	private void considerWithChoice(int firstChoice, int secondChoice, int man) {
		if(marriedWomen[firstChoice-1]) {
    		int rankOfProposal = findRank(firstChoice, man);
    		int rankOfCurrentPartner = findRank(firstChoice, womenPartner[firstChoice-1]);
    		
    		if(rankOfProposal > rankOfCurrentPartner) {
    			int rejected = womenPartner[firstChoice-1];
    			menSingles.add(rejected);
    			marryThem(firstChoice,man);
    		}else {
    			consider(secondChoice, man);
    		}
    	}else {
    		//marry them
    		marryThem(firstChoice,man);
    	}
	}

	/*find the rank of a man in a woman's list*/
	private int findRank(int woman, int man) {
		int pref []  = womenPref[woman-1];
		int i = 0;
		for(int j = 0; j < pref.length; j++) { //for all men in choiced woman's list
			if(pref[j] == man) { //if that man exists
				//System.out.println(j);
				return i; //retunr the index of that man's existence
			}
		}
		
		return i;
	}
	private void marryThem(int woman, int man) {
		womenPartner[woman-1] = man;
		marriedWomen[woman-1]  = true;
	}

	/*CHECK IF THE WOMAN MARRIED OR PREFER WHICHEVER*/
    private void consider(int woman, int man) {
		//check if married
    	if(marriedWomen[woman-1]) {
    		int rankOfProposal = findRank(woman, man);
    		int rankOfCurrentPartner = findRank(woman, womenPartner[woman-1]);
    		
    		if(rankOfProposal > rankOfCurrentPartner) {
    			int rejected = womenPartner[woman-1];
    			menSingles.add(rejected);
    			marryThem(woman,man);
    		}else {
    			menSingles.add(man);
    		}
    	}else {
    		//marry them
    		//System.out.println("marry them dammit");
    		marryThem(woman,man);
    	}
		
	}

	/*CHECKS IF NOT FORBIDDEN FOR EACHOTHER*/
	private boolean notForbidden(int woman, int man) {
		if(woman == 0) return false;
		int pref []  = womenPref[Math.abs(woman)-1];
		if(woman > 0) { //choice is positive
			for(int j =0; j < pref.length; j++) { //for all men in choiced woman list
				if(pref[j] == man) { //if man exists
					return true;
				}
				else if(pref[j] == -man) return false; //if man exist but negative
			}
		}
		return false;
	}
	public int getSingles() {
		// TODO Auto-generated method stub
		return this.menSingles.size();
	}
	
	   public static void main(String[] args) {

		   int k [][] = {{1,3,2}, {1,-3,2} ,{2,-3,-1}
	    	
	    	};
	    	int l [][] = {{2,3,-1}, {3,1,2} ,{-2,1,3}
	    	
	    	};
	    	
	    	ForbiddenPairsAlgTwo fpa = new ForbiddenPairsAlgTwo(k,l);
	    	fpa.printCouples();
	    	fpa.printSingles();
	    }

}
