package main;

import java.io.File;
import java.io.FileNotFoundException;
import stableMarriageWithForbiddenPairs.ForbiddenMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import testing.TestingForbiddenPairs;

public class MainUI {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Pick a Stable Marriage Variant: ");
		System.out.println("1 Stable Marriage With Forbidden Pairs ");
		System.out.println("2 Stable Marriage with Incomplete Pairs ");
		System.out.println("3 Stable Mariage with Polygamous Pairs ");

		int variant = scan.nextInt();

		System.out.println("Pick a Happiness Coffeciant(integer only): ");
		int coef = scan.nextInt();

		System.out.println("Pick the Input size of script with random numbers 2-9: ");
		int size = scan.nextInt();

		if (size < 1) {
			System.out.println("Not correct size input. Try again!");
		}

		if (variant == 1) {
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
		// TODO Auto-generated method stub
		boolean b = runPolygamyScript(size);
	}

	private static boolean runPolygamyScript(int size) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void runIncomplete(int coef, int size) {
		// TODO Auto-generated method stub
		boolean b = runIncompleteScript(size);

	}

	private static boolean runIncompleteScript(int size) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void runForbidden(int coef, int size) {
		// TODO Auto-generated method stub
		File f  = runForbiddenScript(size);
		ForbiddenMain main = new ForbiddenMain();
		int average = getAverage(main.getAlgoOneInstabilities());
		
		System.out.println("Average of instability of algOne: " + average);
		
		average = getAverage(main.getAlgoTwoInstabilities());
		System.out.println("Average of instability of algTwo: " + average);
		
		deleteFile(f);

	}

	private static int getAverage(ArrayList<Integer> a) {
		int t = 0;
		for(int i = 0; i< a.size(); i++) {
			int temp = a.get(i);
			t = t + temp;
		}
		return t/a.size();
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
			//System.out.print(b);
			file = script.getFile();
			
		} catch (IOException e) {
			b = false;
			System.out.println("No State Machine rules found. Please look at the instructions and try again. " + b);
		}
		return file;

	}

}