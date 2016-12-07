import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Kristina Kolibab 
 */

public class Restaurant {
    
    /* globals */
    private final static int MAX_CUSTOMERS_MILLIS = 4000;
    
    static int runNtimes = 0;
    static int numOfWaiters = 0;
    static boolean firstTimeThrough = true;
    //info for waiters and customers
    static String waiterName;
    static int numOfTables;
    static String custName;
    static String courseName;
    static String foods;
    static Table table = new Table();
    //arrays for shit
    static String[] cNames = null;
    static String[][] courses = null; 
    static Table[] tables = null;
    //customer array to start them after their waiter starts
    static Thread[] customerThreadArray = null;
    
    /* needs file IO to read in waiter & customer info from a file
    ask user name of file, allow them to enter name at System.in
    given the file name, program must open file, read in # of waiters,
    waiter names, # of customers for each waiter, customer names,
    and customer courses from the file */
    
    public static void main(String[] args){
        System.out.println("Please enter file name [must use .txt extension]:");
        try{
            //Random rand = new Random();
            //getting users filename
            Scanner scan = new Scanner(new InputStreamReader(System.in));
            String fileName = scan.nextLine();
            //reading from file
            Scanner scanner = new Scanner(new File(fileName));
            //read number of waiters, will loop with this value
            if(firstTimeThrough == true){ 
                if(scanner.hasNextInt()){
                    numOfWaiters = scanner.nextInt();
                    System.out.println("numOfWaiters: " + numOfWaiters);
                }
                firstTimeThrough = false;
            } 
            //now the shit for each waiter
            for(int i = 0; i < numOfWaiters; i++){
                
                waiterName = scanner.next();
                System.out.println("waiterName: " + waiterName);
                if(scanner.hasNextInt()){
                    numOfTables = scanner.nextInt();
                    System.out.println("numOfTables: " + numOfTables);
                }
                //set size of name array
                cNames = new String[numOfTables];
                //set size(s) of namesAndCourses
                courses = new String[numOfTables][3];
                //set size of Table objects array
                tables = new Table[numOfTables];
                //setting array size for customer thread
                customerThreadArray = new Thread[numOfTables];
                
                //now the shit for each customer of the waiter
                for(int n = 0; n < numOfTables; n++){
                    custName = scanner.next();
                    System.out.println("custName: " + custName);
                    for(int j = 0; j < 3; j++){
                        foods = scanner.next();
//                        System.out.println("foods: " + foods);
                        courses[n][j] = foods;
                        System.out.println("courses [" + (j+1) + "]: " + courses[n][j]);
                    }
                    //put in data for each customer
                    Customer customer = new Customer(table, custName);
                    //add customer name into array each time
                    cNames[n] = custName;
                    tables[n] = table;
                    Thread customerThread = new Thread( customer );
                    //put threads into array to call after their waiter thread
                    customerThreadArray[n] = customerThread;
                }
                Waiter waiter = new Waiter(tables, waiterName, cNames, courses);     
                Thread waiterThread = new Thread( waiter );
                waiterThread.start();
                for(int k = 0; k < numOfTables; k++){
                    //start all threads after waiter
                    customerThreadArray[k].start();
                }
            }
            scanner.close();
            
        } catch(FileNotFoundException fnfe){
            System.err.println("File not found");
        } catch(IllegalStateException ise){
            System.err.println(ise.getMessage());
        } 
    }
}
