package lukasz.w;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
	private int a;
	private int b;
	private static String result;
	private static String calculation;
	final static String fileName = "savedOperations.txt";
	static File file = new File(fileName);

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		List<String> list = new ArrayList<String>(5);

		Scanner scan = new Scanner(System.in);

		System.out.println(
				"***Instruction***\n\nType numbers confirming  each of them by enter\nThen choose one of the calculations:\n+-adding\n--substraction\n*-multiplication\n/-division\ne-Exit\n");
		System.out.println("***Last five saved operations***\n");
		calc.readFromFile(fileName);
		System.out.println("***\n");
		
		
		for (;;) {
			calculation = scan.next();
			if (calculation.equals("e")) {
				saveToFile(list, fileName);
				System.exit(0);
			}
			
			/*Here should be some smarter solution of entering equations :) Time is crucial */
			
			calc.a = scan.nextInt();
			calc.b = scan.nextInt();

			switch (calculation) {
			case "+":
				IntegerMath adding = (a, b) -> a + b;
				System.out.println(
						result = calc.a + calculation + calc.b + " = " + calc.operation(adding, calc.a, calc.b));
				saveToQueue(list, result);
				break;
			case "-":
				IntegerMath subtraction = (a, b) -> a - b;
				System.out.println(
						result = calc.a + calculation + calc.b + " = " + calc.operation(subtraction, calc.a, calc.b));
				saveToQueue(list, result);
				break;
			case "*":
				IntegerMath multiplication = (a, b) -> a * b;
				System.out.println(result = calc.a + " " + calculation + " " + calc.b + " = "
						+ calc.operation(multiplication, calc.a, calc.b));
				saveToQueue(list, result);
				break;
			case "/":
				IntegerMath division = (a, b) -> a / b;
				System.out.println(
						result = calc.a + calculation + calc.b + " = " + calc.operation(division, calc.a, calc.b));
				saveToQueue(list, result);
				break;

			/*
			 * case "e": saveToFile(list, fileName); System.exit(0); break;
			 */
			}

		}

	}

	private int operation(IntegerMath math, int a, int b) {
		return math.operate(a, b);
	}

	private void readFromFile(String name) {
		List<String> listFromFile = new ArrayList<String>();
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(file));
			String sCurrentLine;
			int i = 0;

			while ((sCurrentLine = in.readLine()) != null) {

				listFromFile.add(sCurrentLine);
				System.out.println(listFromFile.get(i));
				i++;
			}
			in.close();

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void saveToQueue(List<String> list, String result) {
		if (list.size() <= 4) {
			list.add(result);
		} else {
			list.remove(0);
			list.add(result);
		}
	}

	private static void saveToFile(List<String> list, String name) {

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			for (int i = 0; i < list.size(); i++) {
				out.write(list.get(i).toString() + "\n");

				System.out.println("zapis do pliku " + list.get(i));
			}
			out.close();
		} catch (IOException e1) {
			System.out.println("File " + name + " not exist!");
			e1.printStackTrace();
		}

	}

}
