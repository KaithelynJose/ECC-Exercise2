package Model;
import java.util.*;

import Utility.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class backup {
    private ArrayList<HashMap<String, String>> table;
    Map <String, String> map = new HashMap<>();
    int rowNum, colNum;

    public void readFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("try.txt"));
            Scanner myReader = new Scanner(br);
    
            String newline = System.getProperty("line.separator");
            while(myReader.hasNext()){
                String line = myReader.next();
                boolean hasNextLine = line.contains(newline);
                    
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("try.txt"));

            for(int index = 0; index < this.table.size(); index++){
                Iterator<Map.Entry<String, String>> it = table.get(index).entrySet().iterator();

                while(it.hasNext()){
                    Map.Entry<String, String> entry = it.next();
                    bw.append(entry.getKey() + "="+ entry.getValue());
                    if(!it.hasNext()){
                        break;
                    } else{
                        bw.append(",");
                    }
                }
                bw.append("\n");
            }
            bw.close();
        }catch (Exception e) {
            System.out.println("Error, File Not Found");
            e.printStackTrace();
        }
    }
   
    // public Matrix(ArrayList<HashMap<String, String>> table, int rowNum, int colNum){
    //     this.table = table;
    //     this.rowNum = rowNum;
    //     this.colNum =colNum;
    //     initializeArray(table);
    // }

    public void initializeArray(ArrayList<HashMap<String, String>> table){
        int count = 0;
        for(int rowIndex=0; rowIndex < this.rowNum; rowIndex++){
            HashMap <String, String> map = new HashMap<>();
            for(int colIndex = 0; colIndex < this.colNum; colIndex++){
                map.put(rowIndex+","+colIndex, Reader.getRandValues());  
            } 
            this.table.add(map); 
        }
    }

    public void searchString(){  
        String search = Reader.readString("Enter a string to search ");

        int totalCount = 0;
        for (int rowIndex = 0; rowIndex < table.size(); rowIndex++) {          
            Iterator<Map.Entry<String, String>> it = table.get(rowIndex).entrySet().iterator();
            
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                if(entry.getValue().contains(search)){ 
                    int count = Reader.countChar(entry.getValue(), search);
                    System.out.println("Found "+ search+ " on " + "("+ entry.getKey() +") with " 
                        + count + " instances on key field");
                    ++totalCount;
                }
            }  
        }
        System.out.println("Returned "  +totalCount + " matches.");
    }

    public void editValue(){
        int inputRow = Reader.readInt("\nEnter Row "); 
        int inputCol = Reader.readInt("Enter Column ");

        for(int rowIndex = 0; rowIndex < this.table.size(); rowIndex++){
            Iterator<Map.Entry<String, String>> it = table.get(rowIndex).entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, String> entry = it.next();
                if(entry.getKey().equals(inputRow +","+ inputCol)){
                    System.out.println("Found " + entry.getValue() + " on ("  + inputRow +"," + inputCol +")");
                    String newValue = Reader.readString("Enter New Values ");
                    this.table.get(rowIndex).put(entry.getKey(), newValue);
                    System.out.println("Updated Values on ("  + inputRow +"," + inputCol +") is " + newValue );
                }
            }
        }  
        writeFile();           
    }

    public void resetTable(){
        this.rowNum = Reader.readInt("\nEnter a number for row dimension: "); 
        this.colNum = Reader.readInt("Enter a number for column dimension: ");
        this.table =  new ArrayList<HashMap<String, String>>();
        this.table.clear();

        initializeArray(table);
        writeFile();
    }

    public void printTable(){
        for(int rowIndex=0; rowIndex < this.table.size(); rowIndex++){
            Iterator<Map.Entry<String, String>> it = table.get(rowIndex).entrySet().iterator();
            
            while (it.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>)
                it.next();
                System.out.print("["+ entry.getKey() +"=" + entry.getValue() + "] ");
            }               
            System.out.println();            
        }
        writeFile();
    }

    public void add(){    
        int addChoice = 0;
        System.out.println("\nAdd Option");
        System.out.println("[1] - Add new row dimension"); 
        System.out.println("[2] - Add new column dimension");
        addChoice = Reader.readInt("Enter your choice: ");

        if(addChoice == 1){
            this.rowNum = Reader.readInt("\nEnter a number of new row dimension: ");             
            for(int rowIndex=0; rowIndex < this.rowNum; rowIndex++){
                HashMap <String, String> map = new HashMap<>();      
                for(int colIndex = 0; colIndex < this.colNum; colIndex++){
                    int newRow = this.rowNum + rowIndex;
                    map.put(newRow+","+colIndex, Reader.getRandValues());
                }            
                this.table.add(map);        
            }
        }else if(addChoice == 2){
            int rowIndex, inputCol;
            rowIndex = Reader.readInt("Enter Row");
            inputCol = Reader.readInt("Enter a number of new column dimension");
            
            if(inputCol <= 0){
                inputCol = this.colNum;
            }
                 
            for(int colIndex = 0; colIndex <inputCol; colIndex++){
                int newCol = inputCol + colIndex;
                this.table.get(rowIndex).put(rowIndex+","+newCol, Reader.getRandValues());
            }                  
        }else
            System.out.println("ERROR INPUT"); 
        writeFile(); 
    }

    public void sort() { 
        int sortChoice = 0;
        System.out.println("\nSort Option");
        System.out.println("[1] - Ascending "); 
        System.out.println("[2] - Dscending ");
        sortChoice = Reader.readInt("Enter your choice: ");

        if(sortChoice == 1){
            for(int rowIndex=0; rowIndex < this.table.size(); rowIndex++){
                Iterator<Map.Entry<String, String>> it = table.get(rowIndex).entrySet().iterator();
                
                List<String> sorted = new ArrayList<>(table.get(rowIndex).values());
                Collections.sort(sorted);
    
                for (String sort : sorted){
                    while(it.hasNext()){
                        Map.Entry<String, String> entry = it.next();  
                        table.get(rowIndex).replace(entry.getKey(), sort);
                        break;
                    }
                }
            }  
            System.out.println("Sorted in Ascending order");
        }else if(sortChoice == 2){
            for(int rowIndex=0; rowIndex < this.table.size(); rowIndex++){
                Iterator<Map.Entry<String, String>> it = table.get(rowIndex).entrySet().iterator();
                
                List<String> sorted = new ArrayList<>(table.get(rowIndex).values());
                Collections.sort(sorted, Collections.reverseOrder());
    
                for (String sort : sorted){
                    while(it.hasNext()){
                        Map.Entry<String, String> entry = it.next();  
                        table.get(rowIndex).replace(entry.getKey(), sort);
                        break;
                    }
                }
            }  
            System.out.println("Sorted in Descending order");
        }else 
            System.out.println("ERROR INPUT");   
        writeFile();                      
    }   
}
