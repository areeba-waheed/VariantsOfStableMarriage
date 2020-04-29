package stableMarriageWithForbiddenPairs;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//D:\Program Files\eclipse-workspace\ThesisDemo\src
		
		java.io.File file = new java.io.File("/Users/areeba/eclipse-workspace/ThesisDemo/src/TestForbiddenScript(negatives)2.txt");
		
		try {
			Scanner input = new Scanner(file);
			int limit = input.nextInt();
			int average = limit;
			long timeElapsed = 0;
			//System.out.println(limit);
			while (limit > 0) {
				int n = input.nextInt(); // 2n+n is the size
				//System.out.println(n);
				int whileCount = 0;// n*3;
			
			int [][] m = new int [n][n];
			int [][] w = new int [n][n];
			
			while (whileCount < n * 2) {
				
					for(int i =0; i<n; i++) {
						for(int j=0; j<n; j++) {
							m[i][j] = input.nextInt();
							//System.out.print(m[i][j]);
						}
						//System.out.println();
						whileCount++;
					}
					
					for(int i =0; i<n; i++) {
						for(int j=0; j<n; j++) {
							w[i][j] = input.nextInt();
							//System.out.print(w[i][j]);
						}
						//System.out.println();
						whileCount++;
					}
			}
			
			//ForbiddenPairsAlgOne fpa = new ForbiddenPairsAlgOne(m,w);
			//System.out.println("Singles: " + fpa.getSingles());
			//fpa.printPairs();
			long startTime = System.nanoTime();
			ForbiddenPairsAlgTwo fp = new ForbiddenPairsAlgTwo(m, w); //383049 //196620
			//fp.matchMaker();
			//fp.printCouples();
			//int [] p = reverse(fp.getWomenPartner());
			
			
			//ForbiddenPairsAlgOne fpa = new ForbiddenPairsAlgOne(m,w); //170941 //129207
			//System.out.println("Singles: " + fpa.getSingles());
			//fpa.printPairs();
			//int [] p = fpa.getPair();
			
			 //FIndInstabilitiesWithForbiddenPairs f = new FIndInstabilitiesWithForbiddenPairs(m, w, p); 
			// f.findInstabilties(); 
				//	 System.out.println(f.getInstabilities()); 267515
			long endTime = System.nanoTime();
			 //System.out.println(endTime - startTime);
			timeElapsed += endTime - startTime;
			//fp.printCouples();
			//fp.printSingles();
			limit--;
			}
			System.out.println(timeElapsed / average);
			
		} catch(FileNotFoundException e) {
			System.out.println("No State Machine rules found. Please look at the instructions and try again.");
		}
	}

	private static int[] reverse(int[] q) {
		int [] p = new int [q.length];
		for(int i =0; i<p.length; i++) {
			if(q[i] != 0) {
				int temp = q[i];
				p[temp - 1] = i+1;
			}
		}
		return p;
	}


}
