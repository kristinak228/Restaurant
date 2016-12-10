import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * December 8th, 2016
 * 
 * @author Kristina Kolibab 
 * 
 * This class holds the file I/O from which I read in the users filename and, 
 * from there, read in the file.  I create arrays of waiters, customers, and 
 * tables, from which I incrementally set the correct names and courses to the 
 * corresponding waiters and tables.  I throw FileNotFoundException and 
 * IllegalStateException, in case the user gives an incorrect file name or
 * the program environment is not in the appropriate state for the 
 * requested operation.  After all this is done, I close my scanner, and call 
 * it a day because my program works :).
 */

public class Restaurant {
    
    /* globals */
    private final static int MAX_CUSTOMERS_MILLIS = 4000;
        
    /* arrays and objects */
    private static Waiter[] waiter = null;
    private static Table[] tables = null;
    private static Customer[] customer = null;
    static Table currentTable = null;
    private static int numOfWaiters = 0;
    
    public static void main(String[] args){
        System.out.println("Please enter file name: ");
        try{
            //getting users filename
            Scanner scan = new Scanner(new InputStreamReader(System.in));
            String fileName = scan.nextLine();
            scan.close(); //close this scanner after getting file name
            //reading from file
            Scanner scanner = new Scanner(new File(fileName));

            numOfWaiters = scanner.nextInt(); //first thing read in from file
            waiter = new Waiter[numOfWaiters]; //setting array size for waiters
            
            for(int i = 0; i < numOfWaiters; i++){
                waiter[i] = new Waiter(scanner.next()); //get each waiter's name
                
                int numOfCustomers = scanner.nextInt(); //get each waiter's number of customers
                waiter[i].setArraySizes(numOfCustomers); //set size of customerNames, courses, and tables!
                tables = new Table[numOfCustomers]; //need to set size first
                customer = new Customer[numOfCustomers]; //set size of this array to
                
                for(int j = 0; j < numOfCustomers ; j++){
                    
                    //setting customer info in waiter
                    waiter[i].setCustomerName( j, scanner.next()); //get customers names
                    
                    tables[j] = new Table(); //creates a new table for each customer
                    waiter[i].setTable(j, tables[j]); 
                    
                    //creating customer objects for each customer
                    customer[j] = new Customer(tables[j], waiter[i].getCustomerName(j));
                    
                    //setting courses 1,2,3 for each customer
                    waiter[i].setCourse(j, 0, scanner.next());
                    waiter[i].setCourse(j, 1, scanner.next());
                    waiter[i].setCourse(j, 2, scanner.next());
                    
                    //creating and starting each customer thread
                    Thread customerThread = new Thread(customer[j]);
                    customerThread.start();
                }
                
                //creating and starting each waiter thread
                Thread waiterThread = new Thread(waiter[i]);                
                waiterThread.start();
            }
            scanner.close(); //close scanner that read the entire file
        } catch(FileNotFoundException fnfe){
            System.err.println("File not found");
        } catch(IllegalStateException ise){
            System.err.println(ise.getMessage());
        }
    }
}
