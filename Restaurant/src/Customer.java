import java.util.Random;

/**
 *
 * @author Kristina Kolibab
 */
public class Customer implements Runnable {
    
    //Data
    private final static int MAX_CUSTOMER_MILLIS = 4000; //wait 0-4sec
    private Table table; //Table object this customer sits at
    private String customerName; //name of this customer
    
    //Constructor
    //initializes data
    public Customer(Table table, String customerName){
        this.table = table;
        this.customerName = customerName;
    }
    
    //Method
    @Override
    public void run(){
        Random rand = new Random();
    }
}
