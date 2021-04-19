/*
 * Koosha Kimelman  4/18/21 - Created file
 * */


package prisonerDilemma;

import simStation.*;
import java.awt.*;
import mvc.*;

public class PrisonerDilemmaView extends SimulationView {

    private PrisonerDilemmaSimulation simulation;

    public PrisonerDilemmaView(PrisonerDilemmaSimulation simulation) {
        super(simulation);
        this.simulation = simulation;
        drawAgents = false;
    }

    public void setModel(Model model){
        super.setModel(model);
        simulation = (PrisonerDilemmaSimulation)model;
    }

    public void paintComponent(Graphics gc){
        super.paintComponent(gc);

        Color oldColor = gc.getColor();

        int rad = RADIUS;
        int strat;
        for (Agent agent : simulation.getAgents()) {
            Prisoner prisoner = (Prisoner)agent;
            strat = prisoner.getStrategy();

            switch (strat) {
                case 0: // Always Cheat
                    gc.setColor(Color.RED);
                    break;
                case 1: // Always Cooperate
                    gc.setColor(Color.GREEN);
                    break;
                case 2: // Randomly Cooperate
                    gc.setColor(Color.BLUE);
                    break;
                case 3: // Tit-For-Tat
                    gc.setColor(Color.YELLOW);
                    break;
                default:
                    // Should have no way to get here
            }

            gc.fillOval(agent.getX() - rad, agent.getY() - rad, rad * 2, rad * 2);
        }

        gc.setColor(oldColor);
    }
}
