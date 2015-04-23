import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sort {
	Scanner consolInput = new Scanner(System.in);
	String userInput = "";
	Scanner fileInput;
	int[] inputArray;
	long startTime;
	
	public Sort() {
		System.out.println("Choose an input file:");
		System.out.println("1 - input1.txt  2 - input2.txt  3 - input3.txt  4 - input4.txt");
		userInput = consolInput.nextLine();
		if (userInput.length() != 1 && userInput.charAt(0) != '1' && userInput.charAt(0) != '2' && userInput.charAt(0) != '3' && userInput.charAt(0) != '4') {
			System.out.println("unknow value! please try again");
			while (userInput.length() != 1 && userInput.charAt(0) != '1' && userInput.charAt(0) != '2' && userInput.charAt(0) != '3' && userInput.charAt(0) != '4') {
				userInput = consolInput.nextLine();
			}
		}
		try {
			fileInput = new Scanner(new File("input" + userInput.charAt(0) + ".txt")); //load file from input + 1,2,or 3 then the file type (.txt)
		} catch (FileNotFoundException e) { //If file doesn't exits or there was an error reading the file.
			System.out.println("THERE WAS AN ERROR LOADING \"input" + userInput + ".txt\" OR IT DOES NOT EXITS!");
			e.printStackTrace();
			System.exit(0);
		}
		String infile = fileInput.nextLine();//Read everything in file
		String[] inputStringArray = infile.split(",");//Separates every number with , and replaces them with a \n basicly
		inputArray = new int[inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
			System.out.println(inputArray[i]);
		}
		
		System.out.println("Choose desired sorting type:");
		System.out.println("1 - bubble method (slowest)  2 - Selection method 3 - Table method (fancy!)  4 - QuickSort method");
		userInput = consolInput.nextLine();
		if (userInput.length() != 1 && userInput.charAt(0) != '1' && userInput.charAt(0) != '2' && userInput.charAt(0) != '3' && userInput.charAt(0) != '4') {
			System.out.println("unknow value! please try again");
			while (userInput.length() != 1 && userInput.charAt(0) != '1' && userInput.charAt(0) != '2' && userInput.charAt(0) != '3') {
				userInput = consolInput.nextLine();
			}
		}
		startTime = System.currentTimeMillis();
		
		if (userInput.equals("1"))
		{
			System.out.println("Sorting Through Buddle Method...");
			inputArray = bubbleSort(inputArray);
		}
		if (userInput.equals("2"))
		{
			System.out.println("Sorting Through Selection Method...");
			inputArray = selectionSort(inputArray);
		}
		if (userInput.equals("3"))
		{
			System.out.println("Sorting Through Table Method...");
			inputArray = tableSort(inputArray);
		}
		if (userInput.equals("4"))
		{
			inputArray = quickSort(inputArray);
		}
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(new File("output.txt")));
			String output = "";
			for (int i = 0; i < inputArray.length; i++) {
				output += inputArray[i] + ", ";
				System.out.println(inputArray[i]);
			}
			long totalTime = System.currentTimeMillis() - startTime;
			output += "\nSorted numbers in " + totalTime + " miliseconds.";
			System.out.println("Sorted numbers in " + totalTime + " miliseconds.");
			pw.write(output);
			pw.close();
		} catch (IOException e) {
			System.out.println("FAILED TO PRINT FILE TO \"output.txt\"");
			e.printStackTrace();
			System.exit(0);
		}
	}
	int[] bubbleSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
				}
			}
		}
		return array;
	}
	int[] selectionSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			int smallestNumber = array[j];
			int smallestIndex = j;
			for (int i = j; i < array.length; i++) {
				if (array[i] < smallestNumber) {
					smallestNumber = array[i];
					smallestIndex = i;
				}
			}
			int temp = array[smallestIndex];
			array[smallestIndex] = array[j];
			array[j] = temp;
			}
		return array;
	}
	int[] tableSort(int[] array) {
		int[] tally = new int[1001];
		for (int i = 0; i < array.length; i++) {
			tally[array[i]]++;
		}
		int count = 0;
		for (int i = 0; i < tally.length; i++) {
			for (int j = 0; j < tally[i]; j++) {
				array[count] = i;
				count++;
			}
		}
		return array;
	}
	int[] quickSort(int[] array) {
	
		
		return array;
	}
	public static void main(String[] args) {
		new Sort();
	}
}