/**
 * December 8th, 2016
 * 
 * @author Kristina Kolibab
 * 
 * This class has a default constructor, a serve method and an eat method.  The
 * eat method gets called by the customer when they begin eating, from there
 * they must wait for the waiter to call the serve method, make the customers
 * dish, and bring it into the Table class.  When this is done, the eat method
 * can then return the current dish that the customer should be eating.
 */
public class Table{
    private String course; //name of course
    //flag used to see if table is empty or not (has unfinished course)
    private boolean isEmpty; 
    
    /**
     * Class default constructor that sets the tables current course to null and sets
     * the tables 'isEmpty' variable to true, meaning that the customer has not
     * yet received his or her dish
     */
    public Table(){
        isEmpty = true;
        course = null;
    }
    
    /**
     * synchronized method serve passes in the current course for their 
     * customer when called by the waiter, they wait until the customer is
     * done eating, then set the global course to what was passed in and 
     * notify the other threads.
     * 
     * @param course passes in current course when called by waiter, which is 
     * then used by customer when they call the eat method
     */
    public synchronized void serve(String course){
        while(isEmpty == false){ //waits for customer to finish eating
            try{
                wait();
            } catch(InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
        this.course = course; 
        isEmpty = false; 
        notifyAll();
    }
        
    /**
     * synchronized method eat waits until the waiter thread notifies them that
     * their course is ready.  Once notified, eat returns the current course.
     * 
     * @return course the current course given by the waiter 
     */
    public synchronized String eat(){
        while(isEmpty == true){ //waits for waiter to serve food
            try{
                wait();
            } catch(InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
        isEmpty = true; 
        notifyAll(); //wakes up all threads waiting for a lock
        
        return course;
    }
}
