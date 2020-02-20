package stableMarriageWithIncompleteLists;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		java.io.File file = new java.io.File(
				"/Users/areeba/eclipse-workspace/ThesisDemo/src/IncompleteListScript(zeros).txt");

		try {
			Scanner input = new Scanner(file);
			int limit = input.nextInt();
			int average = limit;
			long timeElapsed = 0;
			while (limit > 0) {
				int n = input.nextInt();
				int whileCount = 0;

				int[][] m = new int[n][n];
				int[][] w = new int[n][n];

				while (whileCount < n * 2) {
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							m[i][j] = input.nextInt();
// System.out.print(m[i][j]);
						}
// System.out.println();
						whileCount++;
					}

					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							w[i][j] = input.nextInt();
// System.out.print(w[i][j]);
						}
// System.out.println();
						whileCount++;
					}
				}

//long startTime = System.nanoTime();

				System.out.println(limit);
//AlgoTwo fp = new AlgoTwo(m, w);
				AlgoOne fp = new AlgoOne(m, w);
				
//int [] q = fp.getWomenPartner();
				int[] p = fp.getWomenPartner();
//int [] p = reverse(q);
				FindInstabilitiesWithIncompleteList f = new FindInstabilitiesWithIncompleteList(m, w, p);
				f.findInstabilties();
				System.out.println(f.getInstabilities());
//System.out.println(limit);
//long endTime = System.nanoTime();
// System.out.println(endTime - startTime);
//timeElapsed += endTime - startTime;
				fp.printCouples();
//fp.printSingles();
				limit--;
			}
//System.out.println(timeElapsed / average);
		} catch (FileNotFoundException e) {
			System.out.println("No State Machine rules found. Please look at the instructions and try again.");
		}

	}

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

}