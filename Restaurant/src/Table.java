/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @author Kristina Kolibab
 */
public class Table {
    //Data
    private String course; //name of course
    //flag used to see if table is empty or not (has unfinished course)
    private boolean isEmpty; 
    private Object obj; //synchronization object (not necessary)
    
    //Constructor
    //default
    public Table(){
        isEmpty = true;
        course = null;
    }
    
    //Methods
    //implements waiter serving a course
    public synchronized void serve(){
        //WAT
    }
    //implements customer eating course
    public synchronized String eat(){
        
        return course;
    }
}
