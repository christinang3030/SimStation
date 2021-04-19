/*
* 4/17/21    Mark Masulis - Implemented class
* */

package plague;

import simStation.*;
import mvc.*;

public class PlagueFactory extends SimulationFactory{

    public Model makeModel(){
        return new PlagueSimulation();
    }

    public String getTitle(){
        return "Plague Simulation";
    }

    public View makeView(Model simulation){
        return new PlagueView((PlagueSimulation)simulation);
    }

    public String about(){
        return "Simulates a viral illness spreading between people.";
    }

}
