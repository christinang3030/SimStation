/*
Edits:
    Christina Ng    4/14/21: created file
*/

package simStation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {

    public StartCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation)model;
        // start
        sim.start();
    }
}
