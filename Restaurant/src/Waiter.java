import java.util.Random;

/**
 * December 8th, 2016
 * 
 * @author Kristina Kolibab
 * 
 * This class holds many methods used for getting and setting the waiters name,
 * customer names, the tables assigned to each customer, as well as for 
 * setting the size of each array.  This also holds the run method in which 
 * the waiter prepares the customers courses and then calls the serve method
 * from Table to serve the customer.
 */

public class Waiter extends Restaurant implements Runnable{
    //Data
    private final static int MAX_WAITER_MILLIS = 4000; //must wait for 0-4sec
    private final static int N_COURSES = 3; //only allow 3 courses
    
    private Table[] tables; //array of table objects waiter waits on
    private String waiterName; //name of waiter
    private String[] customerNames; //names of customers served by waiters
    private String[][] courses; //i customer, j courses
    
    Table table;
    
    /**
     * Method waiterName sets the passed argument to be the waiters name
     * 
     * @param waiterName sets the global variable waiter name to the argument
     * being passed in
     */
    public void setWaiterName(String waiterName){
        this.waiterName = waiterName;
    }
    /**
     * Method getWaiterName returns the waiters name with called
     * 
     * @return watierName the name of the waiter
     */
    public String getWaiterName(){
        return waiterName;
    }    
    /**
     * Method setTable sets current table to current customer
     * 
     * @param customerNum used as an increment to find customers position in 
     * table array
     * @param table the object passed through and set in array of tables
     */
    public void setTable(int customerNum, Table table){
        this.tables[customerNum] = table;
    }
    /**
     * Method getTable returns the table for the current customer 
     * 
     * @param customerNum used as an increment to find the table 
     * @return  tables[customerNum] the current table for which ever customer
     * has been passed through via customer number
     */
    public Table getTable(int customerNum){
        return this.tables[customerNum];
    }
    /**
     * Method getCustomerName returns the name of the current customer
     * 
     * @param customerNum used as an increment to find the customer
     * @return customerNames[customerNum] the current name for which ever
     * customer has been passed through via customer number
     */
    public String getCustomerName(int customerNum){
        return customerNames[customerNum];
    }
    /** 
     * Method setCustomerName sets the current customer name via the customer
     * number and customer name being passed through
     * 
     * @param customerNum used as an increment to find customer
     * @param customerName a string that holds the name of the customer
     */
    public void setCustomerName(int customerNum, String customerName){
        this.customerNames[customerNum] = customerName;
    }
    /**
     * Method size returns the size of the customer array
     * 
     * @return customerNames.length the length of the customer names array
     */
    public int size(){
        return customerNames.length;
    }
    /**
     * Method getCourse returns the current course for the given customer
     * 
     * @param customerNum used as an increment to find customer
     * @param courseNum used as an increment to find course
     * @return the course provided by the course and customer number
     */
    public String getCourse(int customerNum, int courseNum){
        return courses[customerNum][courseNum];
    }
    /**
     * Method setCourse sets the current course 
     * 
     * @param customerNum used as an increment to find customer
     * @param courseNum used as an increment to find course
     * @param course a string that holds the current course
     */
    public void setCourse(int customerNum, int courseNum, String course){
        this.courses[customerNum][courseNum] = course;
    }
    /**
     * Method setArraySizes sets the array sizes for tables, customerNames, 
     * and courses
     * 
     * @param numCustomers an integer used as sizing for setting each array size
     */
    public void setArraySizes(int numCustomers){
        this.tables = new Table[numCustomers];
        this.customerNames = new String[numCustomers];
        this.courses = new String[numCustomers][N_COURSES];
    }
    /**
     * Class constructor sets waiters name
     * 
     * @param waiterName a string used to set the global variable waiter name
     */
    public Waiter(String waiterName){ //new constructor
        this.waiterName = waiterName;
    }
    
    //default constructor, never used
//    public Waiter(Table[] tables, String waiterName, String[] customerNames, 
//            String[][] courses){};  
    
    /**
     * Method run goes through two for loops, one for all the customers, two 
     * for the three dishes they each get.  run then calls the serve method 
     * from Table to pass through the current course being served, waits an 
     * amount of time between 1-4 seconds, then, after the three courses have
     * been served, lets the customer know their courses are done.
     */
    @Override
    public void run(){ //for each customer
        Random rand = new Random();
        
        for(int i = 0; i < customerNames.length; i++){ //all customers
            for(int j = 0; j < N_COURSES; j++){ //3 courses
                
                System.out.println(this.waiterName + " serves " + customerNames[i] + 
                        " " + courses[i][j]);
                //current courses for current customer
                tables[i].serve(courses[i][j]); 
                
                try{
                    Thread.sleep(rand.nextInt(MAX_WAITER_MILLIS));
                } catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            //customer is done with all three meals
            tables[i].serve("Course Done"); 
        } 
    }
}
