 package stableMarriageWithForbiddenPairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * men propose to their first choice, all women accept unless women is already married or if the proposal is in their forbidden list
 * Unengaged women propose to unengaged men that are in womens's preference list and all men accept unless those women who are forbidden to them
 */

//forbidden pairs are negative numbers
 
public class ForbiddenPairsAlgOne {
	private int[][] menPref; 
	/*
	 *     women list
	 * m1  1 -3	2
	 * m2  3 1 -2
	 * m3  -3 2 1 
	 * */

    private int[][] womenPref;
    
    /*
	 *     men list
	 * w1  1 3 -2
	 * w2  3 -1 -2
	 * w3  3 2 1 
	 * */

    private int totalMen;

    private ArrayList<String> pairs;
    private HashMap<Integer, Integer> map;

    private int [] pair;
    private boolean [] marriedWomen; //if married, tru
    private boolean [] marriedMen; // if married, tru
    private int singles;
    
    public ForbiddenPairsAlgOne(int [][] m, int [][] w) { 
    	map = new HashMap<>();
    	totalMen = m.length;
    	pairs = new ArrayList<>();
    	marriedWomen = new boolean[totalMen];
    	marriedMen = new boolean[totalMen];
    	menPref = new int [totalMen][totalMen];
    	womenPref = new int [totalMen][totalMen];
    	copyArray(m, menPref);
    	copyArray(w, womenPref);
    	singles =0;
    	pair = new int [totalMen];
    	
    	alg();
    }
    
    private void copyArray(int [][] old, int [][] current) {
    	for(int i=0; i<old.length; i++) {
    		  for(int j=0; j<old.length; j++) {
    		    current[i][j]=old[i][j];
    		   // System.out.print(current[i][j]);
    		  }
    		 // System.out.println();
    	}
    }
    
    /*
	 * 
	 * while all men are unmarried 
	 * 		man proposes to his first woman
	 * 			if woman taken, then move on to next man
	 * 			if no woman available go to single list
	 * 		and get married
	 * */
    
    
    public void alg() {
    	int whileCount = 0;
    	
    	while(whileCount != totalMen) {
    		int count = 0;
    		for(int i =0; i<totalMen; i++) {
    			int choice = menPref[i][count];
        		//System.out.println("chocie : "+ choice);
        		if(choice > 0) {
        			if(marriedWomen[choice-1] != true && marriedMen[i] != true) { //if woman isnt married
	        			//womenPref[choice-1][i] > 0) { //if for women that man isnt forbidden 		
	        			if(notApproachable(womenPref[choice-1], i+1)) {
	        				pairs.add("(" + (i+1) + "," + choice + ")");
	        				pair[i] = choice;
	        				map.put(i+1, choice);
	        				//System.out.println("(" + (i+1) + "," + choice + ")");
		        			marriedWomen[choice-1] = true;
		        			marriedMen[i] = true;
	        			}
        			}	
        		}
        	}
    		
    		for(int i =0; i<totalMen; i++) {
        		int choice = womenPref[i][count];
        		if(choice > 0) {
	        		if(marriedMen[choice-1] != true && marriedWomen[i] != true) { //if man isnt married
	        			if(notApproachable(menPref[choice-1],i+1)) { //if for man that women isnt forbidden
		        			pairs.add("(" + choice + "," + (i+1) + ")");
		        			pair[choice-1] = i+1;
		        			map.put(choice, i+1);
		        			marriedWomen[i] = true;
		        			marriedMen[choice-1] = true;
		        		}
	        		}
        		}		
        	}
    		
    		count++;
    		whileCount++;
    	}
    	
    	//count the unmarried singles
    	for(int i = 0; i <marriedWomen.length; i++) {
    		if(marriedWomen[i] == false) singles++;
    	}
    	
    	//singles = singles + singles;
    	
    }
    public int [] getPair() {return this.pair;}
    
    private boolean notApproachable(int [] pref, int i) {
		boolean result = false;
		for(int j =0; j < pref.length; j++) {
			if(pref[j] == i) {
				//System.out.println(j);
				return true;
			}
			else if(pref[j] == -i) return false;
		}
		
		return result;
	}

	public int getSingles() { return this.singles;}
    
    public void printPairs() {
    	System.out.println("Forbidden AglOne Pairs: ");
    	for(int i =0; i< pair.length;i++) {
    		if(pair[i] != 0)
    		System.out.println("man " +(i+1)+ " woman " + pair[i]);
    	}
    }
    
    
    public int returnNumberOfStableMarriages() {
    	int result =0;
    	
    	for(int i =1; i<=map.size();i++) {
    		//int choice = map(i)
    	}
    	
    	
    	
    	return result;
    }
}
