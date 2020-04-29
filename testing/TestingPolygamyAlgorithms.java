package testing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestingPolygamyAlgorithms {
	File file;
//randomly generate numbers for multiple times
//random number should be the number of totalMen
//that number will be the limit to the random preference list
	public TestingPolygamyAlgorithms(int size) throws IOException  {
		file = new File("/Users/areeba/eclipse-workspace/ThesisDemo/src/PolygamyScript2.txt");
		if (file.createNewFile())
		{
		    System.out.println("");
		} else {
		    System.out.println("File already exists.");
		}
		 
		//Write Content
		FileWriter writer = new FileWriter(file);
		
		
		
		int limit = size;
		writer.write("" + limit);
		writer.write(System.getProperty( "line.separator" ));
		for (int i = 0; i < limit; i++) {
			int rand = randomNumber(2, size); //totalMen
			//System.out.println(rand);
			writer.write("" + rand);
			writer.write(System.getProperty( "line.separator" ));
			
			int count = 0; 
			while (count < rand*3) {
				ArrayList<Integer> numbers = new ArrayList<Integer>();   
				if(count < rand) {
					while (numbers.size() < rand*2) {

					    int random = randomNumber(1, rand*2);
					    if (!numbers.contains(random)) {
					    	
					        numbers.add(random);
					        //System.out.print(random);
					        writer.write("" +random + " ");
					    	
					    }
					}
				}else {
					while (numbers.size() < rand) {

					    int random = randomNumber(1, rand);
					    if (!numbers.contains(random)) {
					    	
					        numbers.add(random);
					        //System.out.print(random);
					        writer.write("" +random + " ");
					    	
					    }
					}
				}
					
					
					
				//System.out.println();
				writer.write(System.getProperty( "line.separator" ));
				count++;
			}
		}

		writer.close();
		
	}
	
	public File getFile() {return this.file;}
	public static void main(String[] args) throws IOException {
		TestingPolygamyAlgorithms k = new TestingPolygamyAlgorithms(5);
	}

	public static int randomNumber(int min, int max) {
		int rand = (int) (Math.random() * ((max - min) + 1)) + min;
		return rand;
	}
	
	
}
