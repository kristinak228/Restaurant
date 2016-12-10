import java.util.Random;

/**
 * December 8th, 2016
 * 
 * @author Kristina Kolibab
 * 
 * This class has a constructor that assigns each customers table and name.
 * It also has a overridden run method that prompts Table for each course, for 
 * which they then must wait for their waiter to prepare and return to them. 
 * After all three dishes have been given, they let their waiter know they are
 * done.
 */
public class Customer implements Runnable {
    
    //Data
    private final static int MAX_CUSTOMER_MILLIS = 4000; //wait 0-4sec
    private Table table; //Table object this customer sits at
    private String customerName = null; //name of this customer
    private String currentDish = null; //name of ... current dish
    
    /**
     * Class constructor that sets each customers own table and name
     * 
     * @param table
     * @param customerName 
     */
    public Customer(Table table, String customerName){
        this.table = table;
        this.customerName = customerName;
    }
    
    /**
     * Method run is used by each customer to inform the waiter they want 
     * either their first or next course.  Once called, the while loop runs
     * all three courses until the customer is fully done with their 3 course
     * meal.
     */
    @Override
    public void run(){
        Random rand = new Random();
        //customer is ready to order, they call the eat() method
        currentDish = table.eat();
        
        //customer has to wait for the waiter to prepare the dish
        while(!currentDish.equals("Course Done")){ 
            
            System.out.println(customerName + " is eating: " + currentDish);
            try{
                Thread.sleep(rand.nextInt(MAX_CUSTOMER_MILLIS));
            } catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
            currentDish = table.eat(); 
        } 
        
    }
}
