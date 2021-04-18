/*
* 4/17/21    Mark Masulis - Implemented class
* */

package plague;

import simStation.*;

import java.util.Random;

public class PlagueAgent extends Agent{

    private boolean infected;
    private Random rng;
    private int moveSpeed;

    public PlagueAgent(PlagueSimulation ps, int[] pos, boolean infected){
        super("", ps, pos);
        this.infected = infected;
        rng = new Random();
        heading = Heading.random();
        moveSpeed = rng.nextInt(5) + 1;
    }

    public void update(){
        //move
        move(moveSpeed);
        //try to infect a neighbor
        Agent target = simulation.getNeighbor(this, 10);
        if(target != null && target instanceof PlagueAgent && rng.nextInt(100) < ((PlagueSimulation)simulation).virulence){
            ((PlagueAgent)target).infect();
        }
    }

    public boolean isInfected(){
        return infected;
    }

    public synchronized void infect(){
        if(!infected && rng.nextInt(100) > ((PlagueSimulation)simulation).resistance){
            infected = true;
        }
    }

}
