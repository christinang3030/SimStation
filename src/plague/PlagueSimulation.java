/*
* 4/17/21    Mark Masulis - Implemented class
* */

package plague;

import simStation.*;

import java.util.Random;

public class PlagueSimulation extends Simulation{
    public static int virulence = 50;
    public static int resistance = 2;
    private static int numAgents = 50;
    private static int startInfected = 1;

    public void populate(){
        Random r = new Random();
        for(int i = 0; i < startInfected; i++){
            int[] pos = new int[2];
            pos[0] = r.nextInt(getFieldWidth());
            pos[1] = r.nextInt(getFieldHeight());
            addAgent(new PlagueAgent(this, pos, true));
        }
        for(int i = 0; i < numAgents - startInfected; i++){
            int[] pos = new int[2];
            pos[0] = r.nextInt(getFieldWidth());
            pos[1] = r.nextInt(getFieldHeight());
            addAgent(new PlagueAgent(this, pos, false));
        }
    }

    public String[] getStats(){
        String[] baseStats = super.getStats();
        String[] stats = new String[1 + baseStats.length];
        int infected = 0;
        //count infected agents
        for(Agent a: getAgents()){
            if(a instanceof PlagueAgent && ((PlagueAgent) a).isInfected()){
                infected++;
            }
        }
        float percentInfected =  100f * ((float) infected) / ((float)getAgents().size());

        for (int i = 0; i < baseStats.length; i++) {
            stats[i] = baseStats[i];
        }
        stats[stats.length - 1] = "% infected = " + percentInfected;
        return stats;
    }

    public static void main(String[] args){
        SimulationPanel p = new SimulationPanel(new PlagueFactory());
        p.display();
    }
}
