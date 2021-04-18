package simStation;
/*
* Mark Masulis - 4/10: Made file
* Mark Masulis 4/12: Finished implementing class
* */

import mvc.*;

import java.io.*;
import java.util.*;

public class Simulation extends Model{

    private transient Timer timer;
    protected int clock = 0;
    protected ArrayList<Agent> agents;
    //private ArrayList<Thread> threads;
    private boolean started = false;

    private int fieldWidth = 250;
    private int fieldHeight = 250;

    public Simulation(){
        agents = new ArrayList<Agent>();
        //threads = new ArrayList<Thread>();
        startTimer();
    }

    public void start(){
        if(!started) {
            started = true;
            populate();
            for(Agent a: agents){
                Thread t = new Thread(a);
                t.start();
            }
            /*for (Thread t : threads) {
                t.start();
            }*/
        }
    }

    public void suspend(){
        for(Agent a: agents){
            a.suspend();
        }
    }

    public void resume(){
        for(Agent a: agents){
            a.resume();
        }
    }

    //function currently executes without waiting for threads to join. may or may not cause issues.
    public void stop(){
        stopTimer();
        //stop agents
        for(Agent a: agents){
            a.stop();
        }
        //started = false;
    }

    //returns a random agent within the neighbor radius
    public Agent getNeighbor(Agent a, int radius){
        ArrayList<Agent> neighbors = new ArrayList<Agent>();
        //find agents close enough to be considered neighbors
        for(Agent agent: agents){
            if(agent != a) {
                double dist = Math.sqrt(Math.pow(a.getX() - agent.getX(), 2) + Math.pow(a.getY() - agent.getY(), 2));
                if(dist <= radius){
                    neighbors.add(agent);
                }
            }
        }
        if(neighbors.isEmpty()){
            return null;
        }else {
            Random r = new Random();
            return neighbors.get(r.nextInt(neighbors.size())); //choose a random neighbor
        }
    }

    //creates agents and threads
    public void populate(){}

    private void startTimer(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer(){
        timer.cancel();
        timer.purge();
    }

    public String[] getStats(){
        String[] msg = {"#agents = " + agents.size(), "clock = " + clock};
        return msg;
    }

    public void addAgent(Agent a){
        agents.add(a);
        //threads.add(new Thread(a));
    }

    public int getFieldWidth(){
        return fieldWidth;
    }

    public int getFieldHeight(){
        return fieldHeight;
    }

    public ArrayList<Agent> getAgents(){
        return agents;
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        initSupport();
        //restart threads if they are already started
        if(started) {
            for (Agent a : agents) {
                Thread t = new Thread(a);
                t.start();
            }
        }
        //continue timing
        startTimer();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

}

