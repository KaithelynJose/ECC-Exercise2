package Model;
import java.util.*;

import Controller.MatrixController;
import Utility.*;


public class Matrix {
    ArrayList<ArrayList<KeyValue>> table;
    int rowNum, colNum;
    
    public ArrayList<ArrayList<KeyValue>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<KeyValue>> table) {
        this.table = table;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public Matrix(ArrayList<ArrayList<KeyValue>> table){
        this.table = table;
    }

    public void initializeArray(ArrayList<ArrayList<KeyValue>> table){
        this.table.clear();
        this.rowNum = Reader.readInt("\nEnter a number for row dimension: "); 
        this.colNum = Reader.readInt("Enter a number for column dimension: ");
        for(int rowIndex=0; rowIndex < this.rowNum; rowIndex++){
            ArrayList<KeyValue> contents = new ArrayList<KeyValue>();
            for(int colIndex = 0; colIndex < this.colNum; colIndex++){
                contents.add(new KeyValue(rowIndex+","+colIndex, Reader.getRandValues()));  
            } 
            this.table.add(contents); 
        }
    } 
}