/*
Edits:
    Christina Ng    4/17/21: created file
*/

package flocking;

import mvc.*;
import simStation.*;
import java.util.Random;

class Bird extends Agent {
    private int speed;

    public int getSpeed() { return speed; }

    public Bird(FlockingSimulation fs, int[] pos) {
        super("", fs, pos);
        heading = Heading.random();
        speed = Utilities.rng.nextInt(5) + 1;
    }

    public void update() {
        Bird neighbor = (Bird)simulation.getNeighbor(this,10);
        if(neighbor != null) {
            heading = neighbor.heading;
            speed = neighbor.speed;
        }
        move(speed);
    }
}

class FlockingFactory extends SimulationFactory {
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking";}
}

public class FlockingSimulation extends Simulation {

    public void populate() {
        Random r = new Random();
        for(int i = 0; i < 50; i++) {
            int[] pos = new int[2];
            pos[0] = r.nextInt(getFieldWidth());
            pos[1] = r.nextInt(getFieldHeight());
            addAgent(new Bird(this, pos));
        }
    }

    public String[] getStats() {
        int[] speedStats = new int[5];
        String[] statsMsg = new String[5];

        for(Agent a: agents) {
            Bird bird = (Bird)a;
            speedStats[bird.getSpeed()-1]++;
        }

        for(int i = 0; i < 5; i++) {
            statsMsg[i] = "#birds @ speed " + (i+1) + " = " + speedStats[i];
        }

        return statsMsg;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}
