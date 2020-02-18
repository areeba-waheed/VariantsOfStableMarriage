package stableMarriageWithIncompleteLists;

import java.util.ArrayList;

public class AlgoOne {
	
	/*we say that personpisacceptableto personqifpappearson the preference list ofq, andunacceptableotherwise*/
	
	/*
	 * Mis stable if there is no(man,woman) pair (m;w), each of whom is either unmatche dinMan d6n ds the otheracceptable, or prefers the other to his=her partner inM
	 * 
	 * 
	 * 
	 * man asks his n/2 choices only, woman accept and ranks the man
	 * 
	 * 
	 * */
	
		private int[][] menPref;

	    private int[][] womenPref;

	    private ArrayList<Integer> menSingles;

	    private boolean[] womenSingles;

	    private int[] womenPartner;
	    private int[] menPartner;
	    
	    private int totalMen;
	    
	    private boolean [] marriedWomen; //if married, tru
	    private boolean [] manNotAvailable; //either man married of singles list
	    public AlgoOne(int [][] m, int [][] w){
	    	 menPref = m;
	    	 womenPref = w;
	    	 totalMen = m.length;
	    	 menSingles = new ArrayList<>(); 
	    	 womenSingles = new boolean[totalMen]; //single false
	    	 womenPartner = new int [totalMen]; 
	    	 menPartner = new int [totalMen]; 
	    	 marriedWomen = new boolean[totalMen];
	    	 manNotAvailable = new boolean[totalMen];
	    }
	    
	    public void algorithm() {
	    	for(int i = 0; i<totalMen; i++) {
	    		for(int j = 0; j<totalMen/2; j++) {
	    			int choice = menPref[i][j];
	    			if(choice != 0) { 
	    				if(womenSingles[choice - 1] == false) { //if woman is single
	    					if(accepts(i+1, choice)) {
	    						//if man engaged, check if his current partner prefers his ass more than the next choice
	    						if(manNotAvailable[i] == true) {//if man married, then check his ranks
	    							 //true then need to marry his new choice
	    							if(rankChecking(i+1, choice)) {
	    								womenPartner[choice - 1] = i+1;
	    								menPartner[i] = choice;
		    							manNotAvailable[i] = true;
		    							marriedWomen[choice -1] = true;
		    							
		    							
		    							
	    							}
	    						} else {
	    							//marry to his choice
	    							womenPartner[choice - 1] = i+1;
	    							menPartner[i] = choice;
	    							manNotAvailable[i] = true;
	    							marriedWomen[choice -1] = true;
	    						}
	    							
	    						
	    					}
	    				}
	    				//check if that woman accepts(single, and in her list) him
	    					//if yes then, engage them and record her rank of him and add it to married man
	    					
	    			}else {
	    				//put that man with no preference list in singles
	    				manNotAvailable[i] = true; //cuz he's in singles list
	    				menSingles.add(i+1);
	    				break;
	    			}
	    		}
	    		
	    		if(manNotAvailable[i] == false) { //if man never got married even in his first half list, put him in singles list
	    			menSingles.add(i+1);
	    			manNotAvailable[i] = true;
	    		}
	    		
	    	}
	    }

		private boolean rankChecking(int man, int choiceWoman) {
			boolean result = false;
			
			int choiceRank = findRank(man, choiceWoman);
			int currentRank = findRank(man, menPartner[man-1]);
			
			if(choiceRank < currentRank) {
				result = true;
				/*un marry the last woman*/
				womenPartner[menPartner[man-1]-1] = 0;
				marriedWomen[menPartner[man-1]-1] = false;
			}
			
			return result;
		}

		private int findRank(int man, int woman) {
			int result = 0;
			for(int j = 0; j <totalMen; j++) { //for all men in choiced woman's list
				if(womenPref[woman -1][j] == man) { //if that man exists
					//System.out.println(j);
					return j+1; //return the index of that man's existence
				}
			}
			
			return result;
		}

		private boolean accepts(int man, int woman) { //if he exists in her list
			boolean result = false;
			for(int i = 0; i<totalMen; i++) {
				if(womenPref[woman-1][i] == man) {
					return true;
				}
			}
			return result;
		}
	
		public void printCouples() {
	    	for(int i =0; i<womenPartner.length; i++) {
	    		if(womenPartner[i] != 0) {
	    			System.out.println("man "+ womenPartner[i] + ", woman " + (i+1));
	    		}
	    	}
	    }
		
		public void printSingles() {
	    	System.out.println(menSingles);
	    }
}
