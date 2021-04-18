/*
Edits:
    Koosha Kimelman    4/17/21: created file
*/

package prisonerDilemma;

import mvc.*;
import simStation.*;

public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean cheatedLast = false; // Whether this Prisoner was cheated last interaction
    private Strategy strategy;
    private int speed;

    public Prisoner(PrisonerDilemmaSimulation pds, int[] pos, Strategy strategy) {
        super("", pds, pos);
        this.strategy = strategy;
        strategy.myPrisoner = this;
        heading = Heading.random();
        speed = Utilities.rng.nextInt(5) + 1; // 1 - 5
    }

    public boolean cooperate() {
        return strategy.cooperate();
    }

    public void update() {
        Prisoner neighbor = (Prisoner)simulation.getNeighbor(this, 10);
        if (neighbor != null) {
            // Both of these saved to variables for cases with RandomlyCooperate
            boolean coop = cooperate();
            boolean neighborCoop = neighbor.cooperate();

            if (coop) {
                if (neighborCoop) {
                    updateFitness(3);
                    neighbor.updateFitness(3);
                }
                else /* if (!neighborCoop) */ {
                    //updateFitness(0);
                    neighbor.updateFitness(5);
                }
            }
            else /* if (!coop) */ {
                if (neighborCoop) {
                    updateFitness(5);
                    //neighbor.updateFitness(0);
                }
                else /* if (!neighborCoop) */ {
                    updateFitness(1);
                    neighbor.updateFitness(1);
                }
            }

            cheatedLast = !neighborCoop;
        }

        move(speed);
    }

    public void updateFitness(int amt) {
        fitness += amt;
    }

    public int getFitness() {
        return fitness;
    }

    public boolean getCheatedLast() {
        return cheatedLast;
    }

    public int getStrategy() { // I have it return an int so the strategy itself can't be modified if returned
        if (strategy instanceof Cheat) {
            return 0;
        }
        else if (strategy instanceof Cooperate) {
            return 1;
        }
        else if (strategy instanceof RandomlyCooperate) {
            return 2;
        }
        else /* if (strategy instanceof Tit4Tat) */ {
            return 3;
        }
        // Strategy is set upon creation, so it should not be possible to not be set yet
    }
}
