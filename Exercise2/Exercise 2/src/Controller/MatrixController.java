package Controller;
import Model.KeyValue;
import Model.Matrix;
import Utility.Reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AbstractDocument.Content;

public class MatrixController {
    ArrayList<ArrayList<KeyValue>> table;
    Matrix matrix;
    String fileName = "C:/Users/kaaai/Documents/Dev/Exercise2/try.txt";
    
    public MatrixController()throws FileNotFoundException{
        this.table = new ArrayList<ArrayList<KeyValue>>();
        this.matrix = new Matrix(table);
        try {
            this.readFile(fileName, this.matrix);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public void readFile(String fileName, Matrix matrix){
        System.out.println("Read from " + fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Scanner myReader = new Scanner(br);
            ArrayList<KeyValue> contents = new ArrayList<KeyValue>();
            int rowIndex = 0;

            while(myReader.hasNext()){
                String line = myReader.next();
                int colIndex = 0;

                    Matcher match = Pattern.compile("\\(([^)]+)\\)").matcher(line);
                    while(match.find()){
                        String key = rowIndex + "," + colIndex;
                        String value = match.group(1);
                        contents.add(new KeyValue(key, value));
                        ++colIndex;        
                    }
                    if(myReader.hasNextLine()){
                        this.matrix.getTable().add(contents);
                        ++rowIndex;
                        contents = new ArrayList<KeyValue>();
                    }
                    System.out.println(line);
                }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Error, File Not Found");
            e.printStackTrace();
        }
    }
    public void writeFile(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            for(int rowIndex = 0; rowIndex < this.matrix.getTable().size(); rowIndex++){
                for (int colIndex = 0; colIndex < this.matrix.getTable().get(rowIndex).size(); colIndex++) {
                    bw.write("{"+this.matrix.getTable().get(rowIndex).get(colIndex).getKey() + ":("+ this.matrix.getTable().get(rowIndex).get(colIndex).getValue()+")}");
                }
                bw.newLine();
            }
            bw.close();
        }catch (Exception e) {
            System.out.println("Error, File Not Found");
            e.printStackTrace();
        }
    }
   
    public void searchString(){  
        String search = Reader.readString("Enter a string to search ");
        int totalCount = 0;
        
        for (int rowIndex = 0; rowIndex < this.matrix.getTable().size(); rowIndex++) { 
            for (int colIndex = 0; colIndex < this.matrix.getTable().get(rowIndex).size(); colIndex++){
                if(this.matrix.getTable().get(rowIndex).get(colIndex).getValue().contains(search)){
                    int count = Reader.countChar(this.matrix.getTable().get(rowIndex).get(colIndex).getValue(), search);
                    System.out.println("Found "+ search+ " on " + "("+ this.matrix.getTable().get(rowIndex).get(colIndex).getKey() 
                    +") with " + count + " instances on key field");
                    ++totalCount;
                }
            } 
        }
        System.out.println("Returned "  +totalCount + " matches.");
    }

    public void editValue(){
        int inputRow = Reader.readInt("\nEnter Row "); 
        int inputCol = Reader.readInt("Enter Column ");

        for(int rowIndex = 0; rowIndex < this.matrix.getTable().size(); rowIndex++){
            for(int colIndex = 0; colIndex < this.matrix.getTable().get(rowIndex).size(); colIndex++){
                if(this.matrix.getTable().get(rowIndex).get(colIndex).getKey().equals(inputRow + "," + inputCol)){
                    System.out.println("Found " + this.matrix.getTable().get(inputRow).get(inputCol).getValue() + " on ("  + inputRow +"," + inputCol +")");
                    String newValue = Reader.readString("Enter New Values ");
                    this.table.get(rowIndex).get(colIndex).setValue(newValue);
                    System.out.println("Updated Values on ("  + inputRow +"," + inputCol +") is " + newValue );
                    break;
                }
            }
        }  
        writeFile();           
    }

    public void resetTable(){
        this.table.clear();
        this.matrix = new Matrix(this.table);
        matrix.initializeArray(table);
        writeFile(); 
    }

    public void printTable(){
        for(int rowIndex=0; rowIndex < this.matrix.getTable().size() ; rowIndex++){
            for(int colIndex = 0; colIndex < this.matrix.getTable().get(rowIndex) .size(); colIndex++) {
                System.out.printf("["+this.matrix.getTable().get(rowIndex).get(colIndex).getKey() + " = " + this.matrix.getTable().get(rowIndex).get(colIndex).getValue()+"] ");
            }
            System.out.println();
        }
        //writeFile(); 
    }

    public void add(){    
        int addChoice = 0;
        System.out.println("\nAdd Option:");
        System.out.println("[1] - Add new row dimension"); 
        System.out.println("[2] - Add new column dimension");
        addChoice = Reader.readInt("Enter your choice: ");

        if(addChoice == 1){
            int rowInput = Reader.readInt("\nEnter a number of new row dimension: "); 
            int colInput = Reader.readInt("\nEnter a column dimension: "); 
            for(int rowIndex=0; rowIndex < rowInput; rowIndex++){
                ArrayList<KeyValue> contents = new ArrayList<KeyValue>();
                for(int colIndex = 0; colIndex < colInput; colIndex++){
                    //int newRow = rowIndex + rowInput;
                    contents.add(new KeyValue(this.matrix.getRowNum()+rowIndex+","+colIndex, Reader.getRandValues()));
                }            
                this.matrix.getTable().add(contents);        
            }
            writeFile(); 
        }else if(addChoice == 2){
            int rowIndex = Reader.readInt("Enter Row");
            int inputCol = Reader.readInt("Enter a number of new column dimension");

            for(int colIndex = 0; colIndex <inputCol; colIndex++){
                int newCol = colIndex + this.matrix.getColNum();
                this.matrix.getTable().get(rowIndex).add(new KeyValue(rowIndex+","+newCol, Reader.getRandValues()));
            } 
            writeFile();      
        }else
            System.out.println("ERROR INPUT"); 
    }

    public void sort() { 
        int sortChoice = 0;
        System.out.println("\nSort Option:");
        System.out.println("[1] - Ascending "); 
        System.out.println("[2] - Dscending ");
        sortChoice = Reader.readInt("Enter your choice: ");

        ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
        Reader.getListValue(values, this.matrix);

        for(int rowIndex=0; rowIndex < this.matrix.getTable().size(); rowIndex++){
            if(sortChoice == 1){
                Collections.sort(values.get(rowIndex));
                Reader.getSaveValues(values, this.matrix);
            }else if(sortChoice == 2){
                Collections.sort(values.get(rowIndex), Collections.reverseOrder());
                Reader.getSaveValues(values, this.matrix);
            }else 
                System.out.println("ERROR INPUT");     
        }
        writeFile(); 
    }  
}
