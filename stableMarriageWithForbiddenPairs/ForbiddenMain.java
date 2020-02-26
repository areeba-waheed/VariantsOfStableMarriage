package stableMarriageWithForbiddenPairs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ForbiddenMain {
	int[][] m;
	int[][] w;
	int[] p;
	int n;
	ArrayList<Integer> arrayForAlgoOne;
	ArrayList<Integer> arrayForAlgoTwo;
	ArrayList<Integer> singlesForAlgoOne;
	ArrayList<Integer> singlesForAlgoTwo;
	ArrayList<Integer> unhappinessForAlgoOneMAN;
	ArrayList<Integer> unhappinessForAlgoOneWOMAN;
	ArrayList<Integer> unhappinessForAlgoTwoMAN;
	ArrayList<Integer> unhappinessForAlgoTwoWOMAN;
	
	
	public ForbiddenMain() {
		java.io.File file = new java.io.File(
				"/Users/areeba/eclipse-workspace/ThesisDemo/src/TestForbiddenScript(negatives).txt");

		try {
			Scanner input = new Scanner(file);
			int limit = input.nextInt();
			while(limit > 0) {
			n = input.nextInt(); // 2n+n is the size
			// System.out.println(n);
			int whileCount = 0;// n*3;

			m = new int[n][n];
			w = new int[n][n];
			p = new int[n];
			arrayForAlgoOne = new ArrayList<>();
			arrayForAlgoTwo = new ArrayList<>();
			singlesForAlgoOne = new ArrayList<>();
			singlesForAlgoTwo = new ArrayList<>();
			unhappinessForAlgoOneMAN = new ArrayList<>();
			unhappinessForAlgoOneWOMAN = new ArrayList<>();
			unhappinessForAlgoTwoMAN = new ArrayList<>();
			unhappinessForAlgoTwoWOMAN = new ArrayList<>();

			while (whileCount < n * 2) {

				for (int i = 0; i < n; i++) {
					//System.out.print("man " +(i+1) + " : ");
					for (int j = 0; j < n; j++) {
						m[i][j] = input.nextInt();
						// System.out.print(m[i][j] + " ");
					}
					 //System.out.println();
					whileCount++;
				}

				for (int i = 0; i < n; i++) {
					//System.out.print("woman " +(i+1) + " : ");
					for (int j = 0; j < n; j++) {
						w[i][j] = input.nextInt();
						 //System.out.print(w[i][j] + " ");
					}
					 //System.out.println();
					whileCount++;
				}
			}
			
			
			algoOne();
			FIndInstabilitiesWithForbiddenPairs f = new FIndInstabilitiesWithForbiddenPairs(m, w, p); 
			f.findInstabilties(); 
			arrayForAlgoOne.add(f.getInstabilities());
			
			SumOfUnhappiness man1 = new SumOfUnhappiness(m,w,p);
			man1.findUnhappinessOfMan();
			unhappinessForAlgoOneMAN.add(man1.getUnhappinessMan());
			//System.out.println("unhappines of man in algoOne: " + man1.getUnhappinessMan());
			
			man1.findUnhappinessOfWoman();
			unhappinessForAlgoOneWOMAN.add(man1.getUnhappinessWoman());
			//System.out.println("unhappines of woman in algoOne: " + man1.getUnhappinessWoman());
			
			clearPairs();
			
			algoTwo();
			FIndInstabilitiesWithForbiddenPairs fp = new FIndInstabilitiesWithForbiddenPairs(m, w, p); 
			fp.findInstabilties(); 
			arrayForAlgoTwo.add(fp.getInstabilities());
			
			SumOfUnhappiness man = new SumOfUnhappiness(m,w,p);
			man.findUnhappinessOfMan();
			unhappinessForAlgoTwoMAN.add(man.getUnhappinessMan());
			//System.out.println("unhappines of man in algoTwo: " + man.getUnhappinessMan());
			
			man.findUnhappinessOfWoman();
			unhappinessForAlgoTwoWOMAN.add(man.getUnhappinessWoman());
			//System.out.println("unhappines of woman in algoTwo: " + man.getUnhappinessWoman());
		
			
			
			limit--;
			}

		} catch (FileNotFoundException e) {
			System.out.println("No State Machine rules found. Please look at the instructions and try again.");
		}
	}
	
	public ArrayList<Integer> getAlgoOneInstabilities() { return this.arrayForAlgoOne;}
	public ArrayList<Integer> getAlgoTwoInstabilities() { return this.arrayForAlgoTwo;}
	public ArrayList<Integer> getunhappinessForAlgoOneMAN() { return this.unhappinessForAlgoOneMAN;}
	public ArrayList<Integer> getunhappinessForAlgoOneWOMAN() { return this.unhappinessForAlgoOneWOMAN;}
	public ArrayList<Integer> getunhappinessForAlgoTwoMAN() { return this.unhappinessForAlgoTwoMAN;}
	public ArrayList<Integer> getunhappinessForAlgoTwoWOMAN() { return this.unhappinessForAlgoTwoWOMAN;}


	public void algoOne() {
		ForbiddenPairsAlgOne fpa = new ForbiddenPairsAlgOne(m, w);
		singlesForAlgoOne.add(fpa.getSingles());
		//System.out.println("singles for algOne = " + fpa.getSingles());
		//fpa.printPairs();
		p = fpa.getPair();
	}

	public void algoTwo() {
		ForbiddenPairsAlgTwo fp = new ForbiddenPairsAlgTwo(m, w);
		fp.matchMaker();
		singlesForAlgoTwo.add(fp.getSingles());
		//fp.printCouples();
		p = reverse(fp.getWomenPartner());
		//System.out.println("singles for algTwo = " + fp.getSingles());
	}

	private static int[] reverse(int[] q) {
		int[] p = new int[q.length];
		for (int i = 0; i < p.length; i++) {
			if (q[i] != 0) {
				int temp = q[i];
				p[temp - 1] = i + 1;
			}
		}
		return p;
	}
	
	public int findInstability() {
		FIndInstabilitiesWithForbiddenPairs f = new FIndInstabilitiesWithForbiddenPairs(m, w, p);
		f.findInstabilties();
		return f.getInstabilities();
	}
	
	public void clearPairs() {this.p = new int[n];}

	public ArrayList<Integer> getsinglesForAlgoOne() {
		// TODO Auto-generated method stub
		return this.singlesForAlgoOne;
	}

	public ArrayList<Integer> getsinglesForAlgoTwo() {
		// TODO Auto-generated method stub
		return this.singlesForAlgoTwo;
	}

}
