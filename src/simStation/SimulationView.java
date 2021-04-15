package simStation;

/*
 * Koosha Kimelman - 4/14: Made file
 * */

import mvc.*;
import java.awt.*;

public class SimulationView extends View {

    private static final int RADIUS = 3; // Diameter of 6

    public SimulationView(Simulation simulation) {
        super(simulation);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation)model;

        Color oldColor = gc.getColor(); // Not sure if this is needed or not

        gc.setColor(Color.white);
        for (Agent agent : simulation.getAgents()) {
            gc.fillOval(agent.getX() - RADIUS, agent.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
        }

        gc.setColor(oldColor); // Not sure if this is needed or not
    }
}
