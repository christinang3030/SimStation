package simStation;

/*
 * Koosha Kimelman - 4/14: Made file
 * */

import mvc.*;
import java.awt.*;

public class SimulationView extends View {

    protected final int RADIUS = 3; // Diameter of 6
    protected boolean drawAgents = true; // Whether to draw or not (false if overridden)

    public SimulationView(Simulation simulation) {
        super(simulation);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        if (drawAgents) {
            Simulation simulation = (Simulation) model;

            Color oldColor = gc.getColor();

            gc.setColor(Color.white);
            for (Agent agent : simulation.getAgents()) {
                gc.fillOval(agent.getX() - RADIUS, agent.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
            }

            gc.setColor(oldColor);
        }
    }
}
