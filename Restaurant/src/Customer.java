import java.util.Random;

/**
 * @author Kristina Kolibab
 */
public class Customer implements Runnable {
    
    //Data
    private final static int MAX_CUSTOMER_MILLIS = 4000; //wait 0-4sec
    private Table table; //Table object this customer sits at
    private String customerName; //name of this customer
    
    //Constructor
    public Customer(Table table, String customerName){
        this.table = table;
        this.customerName = customerName;
    }
    
    //Method
    @Override
    public void run(){
        Random rand = new Random();
        //customer is ready to order, they call the eat() method
        String currentDish = table.eat();
        
        //test
        System.out.println("Dish being passed through is: " + currentDish);
        System.out.println("Current customer being served: " + customerName);
        
        //customer has to wait for the waiter to prepare the dish
        while(!currentDish.equals("Course Done")){ 
            
            System.out.println(customerName + " is eating: " + currentDish);
            try{
                Thread.sleep(rand.nextInt(MAX_CUSTOMER_MILLIS));
            } catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
            currentDish = table.eat(); 
        } //do i need to reset "course done" ?
        
    }
}
