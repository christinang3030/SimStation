/*
Edits:
    Christina Ng    4/10/21: created file
*/

package simStation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimulationFactory implements AppFactory {

    public Model makeModel() { return new Simulation(); }

    public View makeView(Model m) { return new SimulationView((Simulation)m); }

    public String[] getEditCommands() { return new String[] {"Start","Suspend","Resume","Stop","Stats"}; }

    public Command makeEditCommand(Model model, String type) {
        if (type == "Start") { return new StartCommand(model); }
        else if (type == "Suspend") { return new SuspendCommand(model); }
        else if (type == "Resume") { return new ResumeCommand(model); }
        else if (type == "Stop") { return new StopCommand(model); }
        else if (type == "Stats") { return new StatsCommand(model); }
        return null;
    }

    public String getTitle() { return "SimStation"; }

    public String[] getHelp() {
        return new String[] {"A simulation involves a population of agents moving " +
                "around an environment and interacting with random neighbors.",
                "Use the provided buttons to run through the simulation."};
    }

    public String about() {
        return getTitle() + " version 1.0. Copyright 2021 by Cyberdellic Designs";
    }

}
