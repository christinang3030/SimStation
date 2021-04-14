package simStation;
/*
* Mark Masulis - 4/10: Made file
* Mark Masulis 4/12: Finished implementing class
* */

import mvc.*;
import java.util.*;

public class Simulation extends Model{

    private Timer timer;
    private int clock = 0;
    private ArrayList<Agent> agents;
    private ArrayList<Thread> threads;

    private int fieldWidth;
    private int fieldHeight;

    public Simulation(){
        agents = new ArrayList<Agent>();
        threads = new ArrayList<Thread>();
        startTimer();
    }

    public void start(){
        populate();
        for(Thread t: threads){
            t.start();
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

    public void stop(){
        stopTimer();
        //stop agents
        for(Agent a: agents){
            a.stop();
        }
        //wait for threads
        for(Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
        }
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
        Random r = new Random();
        return neighbors.get(r.nextInt(neighbors.size())); //choose a random neighbor
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

    public String[] stats(){

        return new String[] {};
    }

    public int getFieldWidth(){
        return fieldWidth;
    }

    public int getFieldHeight(){
        return fieldHeight;
    }

    protected ArrayList<Agent> getAgents(){
        return agents;
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

}

