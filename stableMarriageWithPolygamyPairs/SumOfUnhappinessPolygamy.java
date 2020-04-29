package stableMarriageWithPolygamyPairs;

public class SumOfUnhappinessPolygamy {

	private int[][] men;
	private int[][] women;
	private int[][] pairs;
	private int unhappinessMan;
	private int unhappinessWoman;

	public SumOfUnhappinessPolygamy(int[][] m,  int [][] w, int[][] p) {
		this.men = m;
		this.women = w;
		this.pairs = p;
		//this.pairs = new int[m.length][2];
		this.unhappinessMan = 0;
		this.unhappinessWoman = 0;
		//addPairs(p);
	}

	public void printPairs(int[][] pairs2) {
		for (int i = 0; i < pairs2.length; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print(pairs2[i][j]);
			}
			System.out.println();
		}
	}

	public void findUnhappinessOfMan() {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][1] != 0) {
				int rank = findRank(pairs[i][1], men[pairs[i][0] - 1]);
				unhappinessMan += rank;
			}
		}
	}

	public void findUnhappinessOfWoman() {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][1] != 0) {
				int rank = findRank(pairs[i][0], women[pairs[i][1] - 1]);

				unhappinessWoman += rank;
				// System.out.println(unhappinessWoman);
			}
		}
	}

	private int findRank(int n, int[] array) {
		for (int j = 0; j < array.length; j++) {
			if (array[j] == n) {
				// System.out.println("j " +(j +1));
				return j + 1;
			}
		}

		return 0;
	}

	public int getUnhappinessMan() {
		return this.unhappinessMan;
	}

	public int getUnhappinessWoman() {
		return this.unhappinessWoman;
	}

	/*
	 *2 5 1 3 4 6 
6 1 5 4 3 2 
4 1 6 5 2 3 
1 3 2 
3 2 1 
2 1 3 
1 2 3 
2 3 1 
3 1 2 

man 1, woman 1
man 1, woman 2
man 3, woman 4
man 2, woman 5
man 3, woman 6
	 */
/*	public static void main(String[] args) {
		int m[][] = { { 2,5,1,3,4,6 }, { 6,1,5,4,3,2 }, { 4,1,6,5,2,3} };
		int w[][] = { { 1,3,2 }, { 3,2,1 }, { 2,1,3 }, { 1,2,3 }, {2,3,1}, {3,1,2} };
		int p[][] = {{1,1}, {1,2}, {3,4}, {2,5},{3,6} };
		SumOfUnhappinessPolygamy s = new SumOfUnhappinessPolygamy(m, w, p);
		s.findUnhappinessOfMan();
		s.findUnhappinessOfWoman();

		System.out.println("man: " + s.getUnhappinessMan());
		System.out.println("woman: " + s.getUnhappinessWoman());

	}*/
}
