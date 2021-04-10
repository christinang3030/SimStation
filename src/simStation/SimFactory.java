/*
Edits:
    Christina Ng    4/10/21: created file
*/

package simStation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimFactory implements AppFactory {

    public Model makeModel() { return new Simulation(); }

    public View makeView(Model m) { return new SimView((Simulation)m); }

    public String[] getEditCommands() { return new String[] {"Start","Suspend","Resume","Stop","Stats"}; }

    public Command makeEditCommand(Model model, String type) {
        if (type == "Start" || type == "Suspend" || type == "Resume" ||
                type == "Stop" || type == "Stats")
            return new SimCommand(model, type);
        return null;
    }

    public String getTitle() { return "SimStation"; }

    public String[] getHelp() {
        return new String[] {"A simulation involves a population of agents moving " +
                "around an environment and interacting with random neighbors.",
                "Use the provided buttons to run through the simulation."};
    }

    public String about() {
        return "SimStation version 1.0. Copyright 2021 by Cyberdellic Designs";
    }

}
