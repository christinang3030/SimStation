/*
Edits:
    Koosha Kimelman    4/17/21: created file
*/

package prisonerDilemma;

import mvc.*;
import simStation.*;

import java.util.Random;

public class PrisonerDilemmaSimulation extends Simulation {

    public void populate() {
        Random r = new Random();

        Strategy strat = null; // Assigned in switch case below

        for(int i = 0; i < 40; i++) {
            int[] pos = new int[2];
            pos[0] = r.nextInt(getFieldWidth());
            pos[1] = r.nextInt(getFieldHeight());

            // i % 4  (0 - 3)
            switch (i & 3) {
                case 0:
                    strat = new Cheat();
                    break;
                case 1:
                    strat = new Cooperate();
                    break;
                case 2:
                    strat = new RandomlyCooperate();
                    break;
                case 3:
                    strat = new Tit4Tat();
                    break;
                default:
                    // Should have no way to get here
            }
            addAgent(new Prisoner(this, pos, strat));
        }
    }

    public String[] getStats() {
        String[] baseStats = super.getStats();
        String[] stats = new String[baseStats.length + 4];
        int[] avgFitness = new int[4];
        String[] strategies = {"Always Cheat", "Always Cooperate", "Randomly Cooperate", "Tit-For-Tat"}; // For the return String
        String[] colors = {"(red)", "(green)", "(blue)", "(yellow)"}; // For the return String

        Prisoner prisoner;
        for (Agent agent : agents) {
            prisoner = (Prisoner)agent;
            avgFitness[prisoner.getStrategy()] += prisoner.getFitness();
        }

        for (int i = 0; i < 4; i++) {
            stats[i] = "Average fitness for " + strategies[i] + " " + colors[i] + ": " + (avgFitness[i] / 10.0);
        }

        for (int i = 0; i < baseStats.length; i++) {
            stats[i + 4] = baseStats[i];
        }

        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerDilemmaFactory());
        panel.display();
    }
}
