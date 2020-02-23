package stableMarriageWithIncompleteLists;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import stableMarriageWithForbiddenPairs.FIndInstabilitiesWithForbiddenPairs;

public class IncompleteMain {
	int[][] m;
	int[][] w;
	int[] p;
	int n;
	ArrayList<Integer> arrayForAlgoOne;
	ArrayList<Integer> arrayForAlgoTwo;
	ArrayList<Integer> singlesForAlgoOne;
	ArrayList<Integer> singlesForAlgoTwo;
	

	public IncompleteMain() {
		java.io.File file = new java.io.File(
				"/Users/areeba/eclipse-workspace/ThesisDemo/src/IncompleteListScript(zeros).txt");

		try {
			Scanner input = new Scanner(file);
			int limit = input.nextInt();

			while (limit > 0) {
				n = input.nextInt();
				int whileCount = 0;

				m = new int[n][n];
				w = new int[n][n];
				p = new int[n];
				arrayForAlgoOne = new ArrayList<>();
				arrayForAlgoTwo = new ArrayList<>();
				singlesForAlgoOne = new ArrayList<>();
				singlesForAlgoTwo = new ArrayList<>();

				while (whileCount < n * 2) {

					for (int i = 0; i < n; i++) {
						//System.out.print("man " + (i + 1) + " : ");
						for (int j = 0; j < n; j++) {
							m[i][j] = input.nextInt();
							//System.out.print(m[i][j] + " ");
						}
						//System.out.println();
						whileCount++;
					}

					for (int i = 0; i < n; i++) {
						//System.out.print("woman " + (i + 1) + " : ");
						for (int j = 0; j < n; j++) {
							w[i][j] = input.nextInt();
							//System.out.print(w[i][j] + " ");
						}
						//System.out.println();
						whileCount++;
					}
				}

				algoOne();
				FindInstabilitiesWithIncompleteList f = new FindInstabilitiesWithIncompleteList(m, w, p);
				f.findInstabilties();
				System.out.println("instability for algOne = " + f.getInstabilities());
				arrayForAlgoOne.add(f.getInstabilities());
				
				clearPairs();
				
				algoTwo();
				FIndInstabilitiesWithForbiddenPairs fp = new FIndInstabilitiesWithForbiddenPairs(m, w, p);
				fp.findInstabilties();
				System.out.println("instability for AlgoTwo = " + fp.getInstabilities());
				arrayForAlgoTwo.add(fp.getInstabilities());
				
				
				limit--;
			}

		} catch (FileNotFoundException e) {
			System.out.println("No State Machine rules found. Please look at the instructions and try again.");
		}
	}

	public static void main(String[] args) {

	}
	
	public void clearPairs() {this.p = new int[n];}

	private static int[] reverse(int[] q) {
		int[] p = new int[q.length];
		for (int i = 0; i < q.length; i++) {
			if (q[i] != 0) {
				int temp = q[i];
				p[temp - 1] = i + 1;
			}
		}
		return p;
	}

	public ArrayList<Integer> getAlgoOneInstabilities() {
		return this.arrayForAlgoOne;
	}

	public ArrayList<Integer> getAlgoTwoInstabilities() {
		return this.arrayForAlgoTwo;
	}
	
	public ArrayList<Integer> getsinglesForAlgoOne() {
		return this.singlesForAlgoOne;
	}
	
	public ArrayList<Integer> getsinglesForAlgoTwo() {
		return this.singlesForAlgoTwo;
	}

	public void algoOne() {
		AlgoOne fpa = new AlgoOne(m, w);
		singlesForAlgoOne.add(fpa.getSingles());
		fpa.printSingles();
		fpa.printCouples();
		p = reverse(fpa.getWomenPartner());
		System.out.println("singles for algOne = " + fpa.getSingles());
	}

	public void algoTwo() {
		AlgoTwo fp = new AlgoTwo(m, w);
		singlesForAlgoTwo.add(fp.getSingles());
		fp.printSingles();
		fp.printCouples();
		p = reverse(fp.getWomenPartner());
		System.out.println("singles for algTwo = " + fp.getSingles());
		
	}
}
