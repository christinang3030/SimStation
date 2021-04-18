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

    public String getTitle() {
        return "Prisoner's Dilemma Tournament";
    }
}
