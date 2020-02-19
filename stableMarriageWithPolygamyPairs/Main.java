package stableMarriageWithPolygamyPairs;

import java.io.FileNotFoundException;

import java.util.Scanner;

public class Main {

		public static void main(String[] args) {
			java.io.File file = new java.io.File("/Users/areeba/eclipse-workspace/ThesisDemo/src/PolygamyScript2.txt");// "/Users/areeba/eclipse-workspace/ThesisDemo/src/WeirdList.txt");

			try {
				Scanner input = new Scanner(file);
				int limit = input.nextInt();
				int average = limit;
				long timeElapsed = 0;
				//System.out.println(limit);
				while (limit > 0) {
					
					int n = input.nextInt(); // 2n+n is the size
					System.out.println(n);
					int whileCount = 0;// n*3;

					int[][] m = new int[n][n * 2];
					int[][] w = new int[n * 2][n];

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

					// ForbiddenPairsAlgOne fpa = new ForbiddenPairsAlgOne(m,w);
					// System.out.println("Singles: " + fpa.getSingles());
					// fpa.printPairs();
					long startTime = System.nanoTime();
					//PloygamyAlgorithmWithRounds fp = new PloygamyAlgorithmWithRounds(m, w);
					PolygamyMarriageTimesMen fp = new PolygamyMarriageTimesMen(m,w);
					fp.alg();
					fp.printCouples();
					int [] q = fp.getPairs();
					int [][] p = makePairs(q);
					FindInstabilitiesForPolygamy f = new FindInstabilitiesForPolygamy(m,w,p);
					//f.findInstabilties();
					System.out.println(f.getInstabilities());
					long endTime = System.nanoTime();
					//System.out.println(endTime - startTime);
					timeElapsed += endTime - startTime;
					//System.out.println(timeElapsed);
					//fp.printCouples();
					// fp.printSingles();
					limit--;
				}
				System.out.println(timeElapsed/average);

			} catch (FileNotFoundException e) {
				System.out.println("No State Machine rules found. Please look at the instructions and try again.");
			}

		}

		private static int[][] makePairs(int [] q) {
			int [][] p = new int [q.length][2];
			for(int i = 0; i< q.length;i++) {
				p[i][1]=q[i];
				p[i][0] = i+1;
			}
			return p;
		}

		
	

}
