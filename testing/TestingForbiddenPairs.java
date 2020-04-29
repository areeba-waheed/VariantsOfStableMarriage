package testing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestingForbiddenPairs {
	public File file;
//randomly generate numbers for multiple times
//random number should be the number of totalMen
//that number will be the limit to the random preference list
	public TestingForbiddenPairs(int size) throws IOException {
		// create file
		file = new File("/Users/areeba/eclipse-workspace/ThesisDemo/src/TestForbiddenScript(negatives).txt");
		if (file.createNewFile()) {
			System.out.println();
		} else {
			System.out.println("File already exists.");
		}

		// Write Content
		FileWriter writer = new FileWriter(file);

		int limit = size;
		writer.write("" + limit);
		writer.write(System.getProperty("line.separator"));
		for (int i = 0; i < limit; i++) {
			int rand = randomNumber(2, size);
			//System.out.println(rand);
			writer.write("" + rand);
			writer.write(System.getProperty("line.separator"));
			int negativeRandom = 0 - rand;
			int count = rand * 2;
			while (count != 0) {
				ArrayList<Integer> numbers = new ArrayList<Integer>();
				Random randomGenerator = new Random();
				while (numbers.size() < rand) {

					int random = randomNumber(negativeRandom, rand);
					if (!(numbers.contains(random)) && random != 0 && !(numbers.contains(-random))) {

						numbers.add(random);
						//System.out.print(random);
						writer.write("" + random + " ");

					}
				}

				//System.out.println();
				writer.write(System.getProperty("line.separator"));
				count--;
			}
		}

		writer.close();

	}
	public File getFile() {return this.file;}

	public static void main(String[] args) throws IOException {
		TestingForbiddenPairs t = new TestingForbiddenPairs(100);
		
	}

	public static int randomNumber(int min, int max) {
		int rand = (int) (Math.random() * ((max - min) + 1)) + min;
		return rand;
	}
}
