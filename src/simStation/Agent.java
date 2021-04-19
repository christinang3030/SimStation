package simStation;

/*
 * Mark Masulis - 4/10: Made file
 * Mark Masulis 4/12: Finished implementing class
 * Koosha Kimelman - 4/15: Changed position to int, and updated move() function
 * */

import java.io.Serializable;
import java.math.*;

public abstract class Agent implements Runnable, Serializable{
    private String name;
    protected Simulation simulation;
    private AgentState state = AgentState.READY;
    protected Heading heading = Heading.NORTH;
    private int[] position; // {xpos, ypos}
    private transient Thread thread;

    public Agent(String name, Simulation sim, int[] pos){
        this.name = name;
        simulation = sim;
        position = pos; //should be length 2
    }

    public void run(){
        thread = Thread.currentThread();
        if(state == AgentState.READY){
            state = AgentState.RUNNING;
        }
        while(state != AgentState.STOPPED){
            checkSuspended(); // So agents don't update first when loaded up while suspended
            update();
            //Thread.yield();
            try{
                Thread.sleep(20);
            }catch(InterruptedException ie){
                System.err.println(ie.getMessage());
            }
        }
        try {
            thread.join();
        }catch(InterruptedException ie){
            System.err.println(ie.getMessage());
        }
    }

    private synchronized void checkSuspended(){
        while(state == AgentState.SUSPENDED){
            try{
                wait();
            }catch(InterruptedException ie){
                System.err.println(ie.getMessage());
            }
        }
    }

    public synchronized void start(){
        state = AgentState.READY;
        onStart();
    }

    public synchronized void suspend(){
        state = AgentState.SUSPENDED;
        onInterrupted();
    }

    public synchronized void resume(){
        notify();
        state = AgentState.READY;
    }

    public synchronized void stop(){
        state = AgentState.STOPPED;
        onExit();
    }

    public abstract void update();

    public void move(int steps){

        switch (heading) {
            case NORTH:
                position[1] -= steps;
                break;
            case SOUTH:
                position[1] += steps;
                break;
            case WEST:
                position[0] -= steps;
                break;
            case EAST:
                position[0] += steps;
                break;
            default:
                // Should have no way to get the default case
        }

            // (0, 0) to (fieldWidth - 1, fieldHeight - 1)
        if (position[0] > simulation.getFieldWidth() - 1) {
            position[0] -= simulation.getFieldWidth();
        }
        else if (position[0] < 0) {
            position[0] += simulation.getFieldWidth();
        }

        if (position[1] > simulation.getFieldHeight() - 1) {
            position[1] -= simulation.getFieldHeight();
        }
        else if (position[1] < 0) {
            position[1] += simulation.getFieldHeight();
        }

        /*if(position[0] > simulation.getFieldWidth()){
            position[0] -= simulation.getFieldWidth();
        }
        if(position[1] > simulation.getFieldHeight()){
            position[1] -= simulation.getFieldHeight();
        }*/

        simulation.changed();
    }

    public void onStart(){

    }

    public void onExit(){

    }

    public void onInterrupted(){

    }

    public int getX(){
        return position[0];
    }

    public int getY(){
        return position[1];
    }

    public String getName(){
        return name;
    }
}