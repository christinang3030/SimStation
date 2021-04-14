/*
Edits:
    Christina Ng    4/14/21: created file
*/

package simStation;

import mvc.Command;
import mvc.Model;

public class ResumeCommand extends Command {

    public ResumeCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation)model;
        // resume
        sim.resume();
    }
}