package main;

import java.io.File;
import java.io.FileNotFoundException;
import stableMarriageWithForbiddenPairs.ForbiddenMain;
import stableMarriageWithIncompleteLists.IncompleteMain;
import stableMarriageWithPolygamyPairs.PolygamyMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import testing.TestingForbiddenPairs;
import testing.TestingIncompleteList;
import testing.TestingPolygamyAlgorithms;

public class MainUI {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Pick a Stable Marriage Variant: ");
		System.out.println("1 Stable Marriage With Forbidden Pairs ");
		System.out.println("2 Stable Marriage with Incomplete Pairs ");
		System.out.println("3 Stable Mariage with Polygamous Pairs ");

		int variant = scan.nextInt();

		System.out.println("Pick the Input size of script "
				+ "\n(This number will generate a script x \n many instances of the problem and "
				+ "\nthose problems will be assigned random numbers \n with sizes between the number 2 and x where x >= 2): ");
		int size = scan.nextInt();
		
		//System.out.println("Pick a Happiness Coffeciant(integer only): ");
		int coef = 0;//scan.nextInt();

		if (size <= 1) {
			System.out.println("Not correct size input. Try again!");
		}else if (variant == 1) {
			runForbidden(coef, size);
		} else if (variant == 2) {
			runIncomplete(coef, size);
		} else if (variant == 3) {
			runPolygamy(coef, size);
		} else {
			System.out.println("Not correct input. Try again!");
		}

	}

	private static void runPolygamy(int coef, int size) {
		File f = runPolygamyScript(size);
		PolygamyMain main = new PolygamyMain();

		int average = getAverage(main.getAlgoOneInstabilities());
		int averageOfUnhappinesOfMan = getAverage(main.getunhappinessForAlgoOneMAN());
		int averageOfUnhappinesOfWoman = getAverage(main.getunhappinessForAlgoOneWOMAN());
		//int singles = getAverage(main.getsinglesForAlgoOne());
		//System.out.println("Average of instability of algOne: " + average);
		//System.out.println("Average of singles of algOne: " + singles);
		
		
		//singless = getAverage(main.getsinglesForAlgoTwo());
		//int singless = getAverage(main.getsinglesForAlgoTwo());
		int averagee = getAverage(main.getAlgoTwoInstabilities());
		int averageOfUnhappinesOfMann = getAverage(main.getunhappinessForAlgoTwoMAN());
		int averageOfUnhappinesOfWomann = getAverage(main.getunhappinessForAlgoTwoWOMAN());
		//System.out.println("Average of singles of algTwo: " + singles);

		final Object[][] table = new Object[4][];
		table[0] = new String[] { "", "Algo One", "Algo Two" };
		table[1] = new Object[] { "Average of instability", average, averagee };
		table[2] = new Object[] { "Average of Unhappines of Man", averageOfUnhappinesOfMan, averageOfUnhappinesOfMann };
		table[3] = new Object[] { "Average of Unhappines of Woman", averageOfUnhappinesOfWoman, averageOfUnhappinesOfWomann };

		for (final Object[] row : table) {
		    System.out.format("%30s%15s%15s\n", row);
		}
		deleteFile(f);
		
		int algOne = (average + averageOfUnhappinesOfMan + averageOfUnhappinesOfWoman)*coef;
		int algTwo = (averagee + averageOfUnhappinesOfMann + averageOfUnhappinesOfWomann)*coef;
		
		/*if(algOne > algTwo) System.out.println("Algorithm One WINS");
		else if (algOne < algTwo) System.out.println("Algorithm Two WINS");
		else System.out.println("It's a tie!!"); */
		

	}

	private static File runPolygamyScript(int size) {

		boolean b = false;
		File file = null;
		try {
			TestingPolygamyAlgorithms script = new TestingPolygamyAlgorithms(size);
			b = true;
			//System.out.print(b);
			file = script.getFile();

		} catch (IOException e) {
			b = false;
			System.out.println("No State Machine rules found. Please look at the instructions and try again. " + b);
		}
		return file;

	}

	private static void runIncomplete(int coef, int size) {
		// TODO Auto-generated method stub
		File f = runIncompleteScript(size);
		IncompleteMain main = new IncompleteMain();

		int average = getAverage(main.getAlgoOneInstabilities());
		int singles = getAverage(main.getsinglesForAlgoOne());
		int averageOfUnhappinesOfMan = getAverage(main.getunhappinessForAlgoOneMAN());
		int averageOfUnhappinesOfWoman = getAverage(main.getunhappinessForAlgoOneWOMAN());
		/*System.out.println("Average of instability of algOne: " + average);
		System.out.println("Average of singles of algOne: " + singles);
		System.out.println("Average of Unhappines of Man in algOne: " + averageOfUnhappinesOfMan);
		System.out.println("Average of Unhappines of Woman in algOne: " + averageOfUnhappinesOfWoman);
		*/
		
		int singless = getAverage(main.getsinglesForAlgoTwo());
		int averagee = getAverage(main.getAlgoTwoInstabilities());
		int averageOfUnhappinesOfMann = getAverage(main.getunhappinessForAlgoTwoMAN());
		int averageOfUnhappinesOfWomann = getAverage(main.getunhappinessForAlgoTwoWOMAN());
		/*System.out.println("Average of instability of algTwo: " + average);
		System.out.println("Average of singles of algTwo: " + singles);
		System.out.println("Average of Unhappines of Man in algTwo: " + averageOfUnhappinesOfMan);
		System.out.println("Average of Unhappines of Woman in algTwo: " + averageOfUnhappinesOfWoman);
*/
		
		final Object[][] table = new Object[5][];
		table[0] = new String[] { "", "Algo One", "Algo Two" };
		table[1] = new Object[] { "Average of instability", average, averagee };
		table[2] = new Object[] { "Average of singles", singles, singless };
		table[3] = new Object[] { "Average of Unhappines of Man", averageOfUnhappinesOfMan, averageOfUnhappinesOfMann };
		table[4] = new Object[] { "Average of Unhappines of Woman", averageOfUnhappinesOfWoman, averageOfUnhappinesOfWomann };

		for (final Object[] row : table) {
		    System.out.format("%30s%15s%15s\n", row);
		}

		deleteFile(f);
		int algOne = (average + averageOfUnhappinesOfMan + averageOfUnhappinesOfWoman)*coef;
		int algTwo = (averagee + averageOfUnhappinesOfMann + averageOfUnhappinesOfWomann)*coef;
		
		/*if(algOne > algTwo) System.out.println("Algorithm One WINS");
		else if (algOne < algTwo) System.out.println("Algorithm Two WINS");
		else System.out.println("It's a tie!!");*/

	}

	private static File runIncompleteScript(int size) {
		boolean b = false;
		File file = null;
		try {
			TestingIncompleteList script = new TestingIncompleteList(size);
			b = true;
			//System.out.print(b);
			file = script.getFile();

		} catch (IOException e) {
			b = false;
			System.out.println("No State Machine rules found. Please look at the instructions and try again. " + b);
		}
		return file;
	}

	private static void runForbidden(int coef, int size) {

		// TODO Auto-generated method stub
		File f = runForbiddenScript(size);
		ForbiddenMain main = new ForbiddenMain();
		int average = getAverage(main.getAlgoOneInstabilities());
		int singles = getAverage(main.getsinglesForAlgoOne());
		int averageOfUnhappinesOfMan = getAverage(main.getunhappinessForAlgoOneMAN());
		int averageOfUnhappinesOfWoman = getAverage(main.getunhappinessForAlgoOneWOMAN());
		/*System.out.println("Average of instability of algOne: " + average);
		System.out.println("Average of singles of algOne: " + singles);
		System.out.println("Average of Unhappines of Man in algOne: " + averageOfUnhappinesOfMan);
		System.out.println("Average of Unhappines of Woman in algOne: " + averageOfUnhappinesOfWoman);
*/
		int singless = getAverage(main.getsinglesForAlgoTwo());
		int averagee = getAverage(main.getAlgoTwoInstabilities());
		int averageOfUnhappinesOfMann = getAverage(main.getunhappinessForAlgoTwoMAN());
		int averageOfUnhappinesOfWomann = getAverage(main.getunhappinessForAlgoTwoWOMAN());
		/*System.out.println("Average of instability of algTwo: " + averagee);
		System.out.println("Average of singles of algTwo: " + singless);
		System.out.println("Average of Unhappines of Man in algTwo: " + averageOfUnhappinesOfMann);
		System.out.println("Average of Unhappines of Woman in algTwo: " + averageOfUnhappinesOfWomann);
		*/
		
		final Object[][] table = new Object[5][];
		table[0] = new String[] { "", "Algo One", "Algo Two" };
		table[1] = new Object[] { "Average of instability", average, averagee };
		table[2] = new Object[] { "Average of singles", singles, singless };
		table[3] = new Object[] { "Average of Unhappines of Man", averageOfUnhappinesOfMan, averageOfUnhappinesOfMann };
		table[4] = new Object[] { "Average of Unhappines of Woman", averageOfUnhappinesOfWoman, averageOfUnhappinesOfWomann };

		for (final Object[] row : table) {
		    System.out.format("%30s%15s%15s\n", row);
		}

		deleteFile(f);
		int algOne = (average + averageOfUnhappinesOfMan + averageOfUnhappinesOfWoman)*coef;
		int algTwo = (averagee + averageOfUnhappinesOfMann + averageOfUnhappinesOfWomann)*coef;
		
		/*if(algOne > algTwo) System.out.println("Algorithm One WINS");
		else if (algOne < algTwo) System.out.println("Algorithm Two WINS");
		else System.out.println("It's a tie!!");*/

	}

	private static int getAverage(ArrayList<Integer> a) {
		int t = 0;
		for (int i = 0; i < a.size(); i++) {
			
			t += a.get(i);
		}
		return t / a.size();
	}

	private static void deleteFile(File f) {
		f.delete();
	}

	private static File runForbiddenScript(int size) {
		// TODO Auto-generated method stub
		boolean b = false;
		File file = null;
		try {
			TestingForbiddenPairs script = new TestingForbiddenPairs(size);
			b = true;
			// System.out.print(b);
			file = script.getFile();

		} catch (IOException e) {
			b = false;
			System.out.println("No State Machine rules found. Please look at the instructions and try again. " + b);
		}
		return file;

	}

}
