import java.util.Random;

/**
 *
 * @author Kristina Kolibab
 */

public class Waiter implements Runnable{
    //Data
    private final static int MAX_WAITER_MILLIS = 4000; //must wait for 0-4sec
    private final static int N_COURSES = 3; //only allow 3 courses
    
    private Table[] tables; //array of table objects waiter waits on
    private static String waiterName; //name of waiter
    private String[] customerNames; //names of customers served by waiters
    
    //double array, of courses for each customer of this waiter
    //course[i][j] has the jth course for the ith customer of this waiter
    private String[][] courses; 
    
    //Constructor
    public Waiter(Table[] tables, String waiterName, String[] customerNames, 
            String[][] courses){ //initializes the data
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }
    
    //Method
    public void run(){ //for each customer
        Random rand = new Random();
    }
}

