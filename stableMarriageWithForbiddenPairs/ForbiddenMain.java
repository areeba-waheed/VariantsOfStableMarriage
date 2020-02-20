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

			while (whileCount < n * 2) {

				for (int i = 0; i < n; i++) {
					System.out.print("man " +(i+1) + " : ");
					for (int j = 0; j < n; j++) {
						m[i][j] = input.nextInt();
						 System.out.print(m[i][j] + " ");
					}
					 System.out.println();
					whileCount++;
				}

				for (int i = 0; i < n; i++) {
					System.out.print("woman " +(i+1) + " : ");
					for (int j = 0; j < n; j++) {
						w[i][j] = input.nextInt();
						 System.out.print(w[i][j] + " ");
					}
					 System.out.println();
					whileCount++;
				}
			}
			
			
			algoOne();
			FIndInstabilitiesWithForbiddenPairs f = new FIndInstabilitiesWithForbiddenPairs(m, w, p); 
			f.findInstabilties(); 
			arrayForAlgoOne.add(f.getInstabilities());
			clearPairs();
			
			algoTwo();
			FIndInstabilitiesWithForbiddenPairs fp = new FIndInstabilitiesWithForbiddenPairs(m, w, p); 
			fp.findInstabilties(); 
			arrayForAlgoTwo.add(fp.getInstabilities());
		
			
			
			limit--;
			}

		} catch (FileNotFoundException e) {
			System.out.println("No State Machine rules found. Please look at the instructions and try again.");
		}
	}
	
	public ArrayList<Integer> getAlgoOneInstabilities() { return this.arrayForAlgoOne;}

	public ArrayList<Integer> getAlgoTwoInstabilities() { return this.arrayForAlgoTwo;}

	public void algoOne() {
		ForbiddenPairsAlgOne fpa = new ForbiddenPairsAlgOne(m, w);
		// System.out.println("Singles: " + fpa.getSingles());
		fpa.printPairs();
		p = fpa.getPair();
	}

	public void algoTwo() {
		ForbiddenPairsAlgTwo fp = new ForbiddenPairsAlgTwo(m, w);
		fp.matchMaker();
		fp.printCouples();
		p = reverse(fp.getWomenPartner());
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

}
