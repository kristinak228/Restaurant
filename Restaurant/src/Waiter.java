import java.util.Random;

/**
 * @author Kristina Kolibab
 */

public class Waiter implements Runnable{
    //Data
    private final static int MAX_WAITER_MILLIS = 4000; //must wait for 0-4sec
    private final static int N_COURSES = 3; //only allow 3 courses
    
    private Table[] tables; //array of table objects waiter waits on
    private static String waiterName; //name of waiter
    private String[] customerNames; //names of customers served by waiters
    private String[][] courses; //i customer, j courses
    
    String currentCourse; //helps for letting Table keep up to date
    Table table;
    
    //Constructor
    public Waiter(Table[] tables, String waiterName, String[] customerNames, 
            String[][] courses){ //initializes the data
        this.tables = tables;
        this.waiterName = waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }   
    
    //Method
    @Override
    public void run(){ //for each customer
        
        Random rand = new Random();

        for(int i = 0; i < customerNames.length; i++){ //all customers
            for(int j = 0; j < 3; j++){ //3 courses
                currentCourse = courses[i][j];
                System.out.println(waiterName + " serves " + customerNames[i] + " " + courses[i][j]);
                tables[i].serve(currentCourse); //current courses for current customer
                
                try{
                    Thread.sleep(rand.nextInt(MAX_WAITER_MILLIS));
                } catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            tables[i].serve("Course Done"); //customer is done with all three meals
        } 
    }
}
