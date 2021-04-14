/*
Edits:
    Christina Ng    4/14/21: created file
*/

package simStation;

import mvc.Command;
import mvc.Model;

public class SuspendCommand extends Command {

    public SuspendCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation)model;
        // suspend
        sim.suspend();
    }
}
