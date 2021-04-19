/*
* 4/17/21    Mark Masulis - Implemented class
* */


package plague;

import simStation.*;
import java.awt.*;
import mvc.*;

public class PlagueView extends SimulationView{

    private PlagueSimulation simulation;
    private static final int RADIUS = 2;

    public PlagueView(PlagueSimulation simulation) {
        super(simulation);
        this.simulation = simulation;
        drawAgents = false;
    }

    public void setModel(Model model){
        super.setModel(model);
        simulation = (PlagueSimulation)model;
    }

    public void paintComponent(Graphics gc){
        super.paintComponent(gc);

        Color oldColor = gc.getColor();

        for (Agent agent : simulation.getAgents()) {
            if(((PlagueAgent)agent).isInfected()){
                gc.setColor(Color.RED);
            }else{
                gc.setColor(Color.GREEN);
            }
            gc.fillOval(agent.getX() - RADIUS, agent.getY() - RADIUS, RADIUS * 2, RADIUS * 2);
        }

        gc.setColor(oldColor);
    }
}
