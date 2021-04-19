/*
Edits:
    Christina Ng    4/14/21: created file
    Mark Masulis 4/16/21 Fixed errors
*/

package randomwalk;

import mvc.*;
import simStation.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Random;

class Drunk extends Agent {

    public Drunk(RandomWalkSimulation rws, int[] pos) {
        super("", rws, pos);
        heading = Heading.random();
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

}

class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
    public String about(){
        return "Simulates people moving at changing directions and speeds.";
    }
}

public class RandomWalkSimulation extends Simulation {

    public void populate() {
        Random r = new Random();
        for(int i = 0; i < 15; i++) {
            int[] pos = new int[2];
            pos[0] = r.nextInt(getFieldWidth());
            pos[1] = r.nextInt(getFieldHeight());
            addAgent(new Drunk(this, pos));
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

}
