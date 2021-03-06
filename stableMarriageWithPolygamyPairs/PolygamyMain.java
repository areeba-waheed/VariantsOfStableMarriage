package stableMarriageWithPolygamyPairs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import stableMarriageWithForbiddenPairs.FIndInstabilitiesWithForbiddenPairs;
import stableMarriageWithForbiddenPairs.SumOfUnhappiness;
import stableMarriageWithIncompleteLists.AlgoOne;
import stableMarriageWithIncompleteLists.AlgoTwo;
import stableMarriageWithIncompleteLists.FindInstabilitiesWithIncompleteList;

public class PolygamyMain {
	int[][] m;
	int[][] w;
	int[][] p;
	int n;
	ArrayList<Integer> arrayForAlgoOne;
	ArrayList<Integer> arrayForAlgoTwo;
	ArrayList<Integer> singlesForAlgoOne;
	ArrayList<Integer> singlesForAlgoTwo;
	ArrayList<Integer> unhappinessForAlgoOneMAN;
	ArrayList<Integer> unhappinessForAlgoOneWOMAN;
	ArrayList<Integer> unhappinessForAlgoTwoMAN;
	ArrayList<Integer> unhappinessForAlgoTwoWOMAN;
	
	public PolygamyMain() {
		java.io.File file = new java.io.File("/Users/areeba/eclipse-workspace/ThesisDemo/src/PolygamyScript1.txt");// "/Users/areeba/eclipse-workspace/ThesisDemo/src/WeirdList.txt");

		try {
			Scanner input = new Scanner(file);
			int limit = input.nextInt();
			
			//System.out.println(limit);
			while (limit > 0) {
				
				n = input.nextInt(); // 2n+n is the size
				//System.out.println(n);
				int whileCount = 0;// n*3;

				m = new int[n][n * 2];
				w = new int[n * 2][n];
				arrayForAlgoOne = new ArrayList<>();
				arrayForAlgoTwo = new ArrayList<>();
				singlesForAlgoOne = new ArrayList<>();
				singlesForAlgoTwo = new ArrayList<>();
				unhappinessForAlgoOneMAN = new ArrayList<>();
				unhappinessForAlgoOneWOMAN = new ArrayList<>();
				unhappinessForAlgoTwoMAN = new ArrayList<>();
				unhappinessForAlgoTwoWOMAN = new ArrayList<>();

				while (whileCount < n * 3) {
					// while(input.hasNext()) {
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n * 2; j++) {
							m[i][j] = input.nextInt();
						//	System.out.print(m[i][j]);
						}
						//System.out.println();
						whileCount++;
					}
					for (int i = 0; i < n * 2; i++) {
						for (int j = 0; j < n; j++) {
							w[i][j] = input.nextInt();
						//	System.out.print(w[i][j]);
						}
						//System.out.println();
						whileCount++;
					}
					

				}
				
				algoOne();
				FindInstabilitiesForPolygamy f = new FindInstabilitiesForPolygamy(m, w, p);
				f.findInstabilties();
				//System.out.println("instability for algOne = " + f.getInstabilities());
				arrayForAlgoOne.add(f.getInstabilities());
				
				SumOfUnhappinessPolygamy man1 = new SumOfUnhappinessPolygamy(m,w,p);
				man1.findUnhappinessOfMan();
				//System.out.println("unhappines of man in algoOne: " + man1.getUnhappinessMan());
				unhappinessForAlgoOneMAN.add(man1.getUnhappinessMan());
				
				man1.findUnhappinessOfWoman();
				unhappinessForAlgoOneWOMAN.add(man1.getUnhappinessWoman());
				//System.out.println("unhappines of woman in algoOne: " + man1.getUnhappinessWoman());
				
				clearPairs();
				
				algoTwo();
				FindInstabilitiesForPolygamy fp = new FindInstabilitiesForPolygamy(m, w, p);
				fp.findInstabilties();
				//System.out.println("instability for AlgoTwo = " + fp.getInstabilities());
				arrayForAlgoTwo.add(fp.getInstabilities());
				
				SumOfUnhappinessPolygamy man = new SumOfUnhappinessPolygamy(m,w,p);
				man.findUnhappinessOfMan();
				//System.out.println("unhappines of man in algoTwo: " + man.getUnhappinessMan());
				unhappinessForAlgoTwoMAN.add(man.getUnhappinessMan());

				man.findUnhappinessOfWoman();
				unhappinessForAlgoTwoWOMAN.add(man.getUnhappinessWoman());
				//System.out.println("unhappines of woman in algoTwo: " + man.getUnhappinessWoman());
				
				limit--;
			}

		} catch (FileNotFoundException e) {
			System.out.println("No State Machine rules found. Please look at the instructions and try again.");
		}
	}
	
	public void clearPairs() {this.p = new int[n*2][2];}

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
		PolygamyAlgorithmWIthRounds fpa = new PolygamyAlgorithmWIthRounds(m, w);
			//singlesForAlgoOne.add(fpa.getSingles());
			//fpa.printSingles();
		//fpa.printCouples();
		p = makePairs(fpa.getPairs());
		//System.out.println("singles for algOne = " + fpa.getSingles());
	}

	public void algoTwo() {
		PolygamyMarriageTimesMen fp = new PolygamyMarriageTimesMen(m, w);
			//singlesForAlgoTwo.add(fp.getSingles());
			//fp.printSingles();
		//fp.printCouples();
		
		p = makePairs(fp.getPairs());
		//System.out.println("singles for algTwo = " + fp.getSingles());
		
	}
	
	public int [] [] makePairs(int [] pair) {
		int [][] pairs = new int [pair.length][2];
		for(int i = 0; i < pair.length; i++) {
			pairs[i][0] = pair[i];
			pairs[i][1] = i+1;
		}
		return pairs;
	}

	public ArrayList<Integer> getunhappinessForAlgoOneMAN() { return this.unhappinessForAlgoOneMAN;}
	public ArrayList<Integer> getunhappinessForAlgoOneWOMAN() { return this.unhappinessForAlgoOneWOMAN;}
	public ArrayList<Integer> getunhappinessForAlgoTwoMAN() { return this.unhappinessForAlgoTwoMAN;}
	public ArrayList<Integer> getunhappinessForAlgoTwoWOMAN() { return this.unhappinessForAlgoTwoWOMAN;}
}
