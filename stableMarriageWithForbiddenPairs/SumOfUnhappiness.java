package stableMarriageWithForbiddenPairs;

public class SumOfUnhappiness {
	private int[][] men;
	private int[][] women;
	private int[][] pairs;
	private int unhappinessMan;
	private int unhappinessWoman;

	public SumOfUnhappiness(int[][] m,  int [][] w, int[] p) {
		this.men = m;
		this.women = w;
		this.pairs = new int[m.length][2];
		this.unhappinessMan = 0;
		this.unhappinessWoman = 0;
		addPairs(p);
	}

	private void addPairs(int[] pairs2) {
		for (int i = 0; i < pairs2.length; i++) {
			// System.out.println(pairs2[i]);
			pairs[i][0] = i + 1;
			pairs[i][1] = pairs2[i];
		}
	}

	public void findUnhappinessOfMan() {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][1] != 0) {
				int rank = findRank(pairs[i][1], men[i]);
				unhappinessMan += rank;
			}
		}
	}
	public void findUnhappinessOfWoman() {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i][1] != 0) {
				int rank = findRank(pairs[i][0], women[pairs[i][1] - 1 ]);
				
				unhappinessWoman += rank;
				//System.out.println(unhappinessWoman);
			}
		}
	}

	private int findRank(int n, int[] array) {
		for (int j = 0; j < array.length; j++) {
			if (array[j] == n) { 
				//System.out.println("j " +(j +1));
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
	 * man 2, woman 1
man 3, woman 2
man 4, woman 3
man 1, woman 4
	 * 
	 * 
	 * 1 4 0 0 
1 4 0 0 
3 2 0 0 
4 3 2 1 
4 3 2 1 
4 2 3 1 
4 0 0 0 
1 0 0 0
	 */
	public static void main(String[] args) {
		int m[][] = { { 1, 4, 0, 0 }, { 1, 4, 0, 0 }, { 3, 2, 0, 0 }, { 4, 3, 2, 1 }};
		int w[][] = {{ 4, 3, 2, 1 }, { 4, 2, 3, 1 }, { 4,0,0,0}, { 1,0,0,0} };
		int p[] = { 4, 1, 2, 3};
		SumOfUnhappiness s = new SumOfUnhappiness(m,w, p);
		s.findUnhappinessOfMan();
		s.findUnhappinessOfWoman();
		
		System.out.println("man: "+ s.getUnhappinessMan());
		System.out.println("woman: " + s.getUnhappinessWoman());
		

	}
}
