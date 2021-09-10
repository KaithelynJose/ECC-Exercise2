import java.io.FileNotFoundException;

import Controller.MatrixController;
import Utility.Reader;
import View.MatrixView;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("JAVA EXERCISE 2");

        //ArrayList<ArrayList<KeyValue>> table = new ArrayList<ArrayList<KeyValue>>();
        MatrixController matrixController = new MatrixController();
        int choice = 0;
        do{
                MatrixView.printMenu();   
                choice = Reader.readInt("Enter your choice ");
            
                switch(choice) {
                        case 1: System.out.println("-------------------");
                                System.out.println("SEARCH");
                                matrixController.searchString();
                                break;
                        case 2: System.out.println("-------------------");
                                System.out.println("EDIT");
                                matrixController.editValue();
                                break;
                        case 3: System.out.println("-------------------");
                                System.out.println("PRINT");
                                matrixController.printTable();
                                break;
                        case 4: System.out.println("-------------------");
                                System.out.println("RESET");
                                matrixController.resetTable();
                                break;
                        case 5: System.out.println("-------------------");
                                System.out.println("ADD");
                                matrixController.add();
                                break;
                        case 6: System.out.println("-------------------");
                                System.out.println("SORT");
                                matrixController.sort();
                                break;
                        case 7: System.out.println("-------------------");
                                System.out.println("EXIT");
                                System.exit(0);
                                break; 
                        default : System.out.println("Invalid choice");
                }
        }while (choice!= 7);  
    }
}
