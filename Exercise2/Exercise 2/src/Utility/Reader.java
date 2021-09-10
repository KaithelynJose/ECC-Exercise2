package Utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import Model.Matrix;

import java.util.ArrayList;
import java.util.Random;


public class Reader {
	private static final int DEFAULT_NUM_CHAR = 3;
    Scanner sc = new Scanner(System.in);

    public static String getRandValues() {
		String values = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder SB = new StringBuilder();  
        Random random = new Random();     
        while(SB.length() < DEFAULT_NUM_CHAR){
            int i = (int) (random.nextFloat() * values.length());
            SB.append(values.charAt(i));
        }
        String valStr = SB.toString();    
        return valStr;
    }

	public static int countChar(String str, String search){
		int count = 0;

		String a[] = str.split("");

		for(int i =0; i < a.length; i++){
			if(search.equals(a[i])){
				count++;
			}
		}
		return count;
	}
	public static void getListValue(ArrayList<ArrayList<String>> values, Matrix matrix){
		for (int rowIndex = 0; rowIndex < matrix.getTable().size(); rowIndex++){
			ArrayList<String> rowList = new ArrayList<String>();
			for (int colIndex = 0; colIndex < matrix.getTable().get(rowIndex).size(); colIndex++){
				String concatValue = matrix.getTable().get(rowIndex).get(colIndex).getValue();
				rowList.add(concatValue);
			}
			values.add(rowList);
		}
	}

	public static void getSaveValues(ArrayList<ArrayList<String>> values, Matrix matrix){
		for (int rowIndex = 0; rowIndex < matrix.getTable().size(); rowIndex++){
			for (int colIndex = 0; colIndex < matrix.getTable().get(rowIndex).size(); colIndex++){
				matrix.getTable().get(rowIndex).get(colIndex).setValue(values.get(rowIndex).get(colIndex));
			}
		}
	}   

    public static BufferedReader getReader() {
		BufferedReader reader= new BufferedReader (new InputStreamReader(System.in));
		return reader;
	}

    public static String readString(String message) {
		String input= null;
		try {
			System.out.print(message + ": ");
			input= getReader().readLine();
		}
		catch(Exception e) {
		}
		return input;
	}
	
    public static int readInt(String message) {
        int input = 0;
		try {
			System.out.print(message + ": ");
			input= Integer.parseInt(getReader().readLine());
            return input;
		}
        catch(NumberFormatException  e){
            System.out.println("Must be an integer ");
            return readInt(message);
        }
		catch(Exception e) {
            System.out.println("Must be an integer ");
            return readInt(message);
		}    
	}
	
	public static float readFloat(String message) {
		float input= (float) 0.00;
		try {
			System.out.print(message + " ");
			input= Float.parseFloat(getReader().readLine());
		}
		catch(Exception e) {
		}
		return input;
	}
	
	public static double readDouble(String message) {
		double input= 0.00;
		try {
			System.out.print(message + " ");
			input= Double.parseDouble(getReader().readLine());
		}
		catch(Exception e) {
		}
		return input;
	}
}
