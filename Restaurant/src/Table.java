/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Kristina Kolibab
 */
public class Table{
    private String course; //name of course
    //flag used to see if table is empty or not (has unfinished course)
    private boolean isEmpty; 
    
    //Default constructor
    public Table(){
        isEmpty = true;
        course = null;
    }
    
    //Methods
    //implements waiter serving a course
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
        
    //implements customer eating course
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
