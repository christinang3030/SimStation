/*
Edits:
    Koosha Kimelman    4/17/21: created file
*/

package prisonerDilemma;

import mvc.*;
import simStation.*;

public class PrisonerDilemmaFactory extends SimulationFactory {

    public Model makeModel() {
        return new PrisonerDilemmaSimulation();
    }

    public View makeView(Model simulation) {
        return new PrisonerDilemmaView((PrisonerDilemmaSimulation)simulation);
    }

    public String getTitle() {
        return "Prisoner's Dilemma Tournament";
    }

    public String about(){
        return "Simulates prisoners playing the prisoner's dilemma with varying strategies.";
    }
}
