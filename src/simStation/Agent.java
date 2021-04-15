package simStation;

/*
 * Mark Masulis - 4/10: Made file
 * Mark Masulis 4/12: Finished implementing class
 * */

import java.io.Serializable;
import java.math.*;

public abstract class Agent implements Runnable, Serializable{
    private String name;
    private Simulation simulation;
    private AgentState state;
    private int heading = 0; //angle from 0 to 360
    private int[] position; // {xpos, ypos}
    private Thread thread;

    public Agent(String name, Simulation sim, int[] pos){
        this.name = name;
        simulation = sim;
        position = pos; //should be length 2
    }

    public void run(){
        thread = Thread.currentThread();
        state = AgentState.RUNNING;
        while(state != AgentState.STOPPED){
            update();
            //Thread.yield();
            try{
                Thread.sleep(20);
            }catch(InterruptedException ie){
                System.err.println(ie.getMessage());
            }
            checkSuspended();
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
        double angleRadians = Math.toRadians((double)(heading % 360));
        double xdelta = Math.cos(angleRadians);
        double ydelta = -Math.sin(angleRadians);

        position[0] += xdelta * steps;
        position[1] += ydelta * steps;

        if(position[0] > simulation.getFieldWidth()){
            position[0] -= simulation.getFieldWidth();
        }
        if(position[1] > simulation.getFieldHeight()){
            position[1] -= simulation.getFieldHeight();
        }

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